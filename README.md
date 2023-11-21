Para teste, pode utlizar o swagger ao executar o projeto:
https://localhost:{PORTA}/swagger-ui/index.html

Existe também o arquivo com os scripts sql no diretório SQL

ATENÇÃO! Atentar para as informações escritas nas descrições, como o formato da data (orderDate).
Foi criado, de forma abstrata, API de criação, tanto de uma lista de pedidos de clientes, como também lista de clientes.
Padrões de projeto utilizados:
VO -> Value Object
FACADE -> Fachada no portugês
Strategy
VH -> View Helper

Além do padrão arquitetural MVC -> Model View Controller.

Segue exemplo de payload para criação de Pedidos:

[
    {
        "controlCode": "4571",
        "productName": "produto 5",
        "productValue": 400.00,
        "customerCode": 10
    }
]
