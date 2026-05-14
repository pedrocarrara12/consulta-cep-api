# Consulta CEP API

Projeto de portfolio com backend em Spring Boot e frontend simples em HTML, CSS e JavaScript para consulta de CEP usando a API ViaCEP.

## Estrutura

```text
consulta-cep-api/
├── backend/
│   └── API Spring Boot
└── frontend/
    └── Interface web simples
```

## Backend

O backend expoe os endpoints:

- `GET /cep/{cep}`
- `GET /cep/historico`
- `DELETE /cep/historico/{id}`
- `DELETE /cep/historico`

Configure dados locais do banco por variaveis de ambiente quando publicar ou rodar fora do ambiente de desenvolvimento.

## Frontend

O frontend consome a API em `http://localhost:8080`.

Para rodar localmente:

```bash
cd frontend
python -m http.server 5500
```

Acesse `http://localhost:5500`.
