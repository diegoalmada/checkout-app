# Processo de build da aplicação Checkout
Esta aplicação visa realizar a compra de produtos pré-definidos por uma API, cuja fonte de dados 
é um arquivo JSON. O objetivo é apresentar a descrição dos produtos com seus valores e descontos, além do resumo com o valor total,
valor total com desconto e o valor total do desconto. 

Para resgatar o percentual de desconto, é consumido um cliente GRPC que é registrado como container "discount" no docker-compose.
Caso o cliente GRPC esteja off, o valor de desconto será igual a ZERO.

Segue abaixo o passo a passo para o build da aplicação. Você deve estar na pasta root do projeto para executar os comandos abaixo: 


#### Gerar pacote jar do checkout
```
./mvnw package
```
Além de gerar o pacote jar do projeto, este gerará os testes unitários.

#### Incializar os container "discount" e "checkout"
```
docker-compose up --build -d
```

Com os container inicializados, você pode acessar o Postman e realizar a seguinte chamada:
```
POST: http://localhost:8080/checkout

Body: 
{
    "products": [
        {
            "id": 5,
            "quantity": 10
        },
        {
            "id": 2,
            "quantity": 4
        },
        {
            "id": 3,
            "quantity": 10
        }
    ]
}
```
O status de retorno deverá ser 200 (Status Ok).

Caso informar informar um valor para products no body, como null ou array vazio, 
o retorno deverá ser 404 (Bad Request), com a seguinte mensagem:
```
{
"httpStatus": "BAD_REQUEST",
"message": "Ocorreu um problema ao informar o(s) produto(s)"
}
```

A outra possibilidade é parar o container "discount", através do comando abaixo.
```
docker compose stop discount
```
Com isso, o valor de desconto ao consumir a API será ZERO.
