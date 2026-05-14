# Frontend - Consulta CEP

Interface simples em HTML, CSS e JavaScript para consumir a API Spring Boot do projeto.

## Como executar

1. Inicie o backend na pasta `backend`.
2. Sirva esta pasta em uma porta local, por exemplo:

```bash
python -m http.server 5500
```

3. Acesse `http://localhost:5500`.

## Configuracao

A URL da API fica no arquivo `app.js`:

```js
const API_BASE_URL = "http://localhost:8080";
```

Nao coloque tokens, senhas ou chaves privadas no frontend. Tudo que esta em HTML, CSS ou JavaScript pode ser visto no navegador.
