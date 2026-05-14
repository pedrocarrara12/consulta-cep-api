const API_BASE_URL = "http://localhost:8080";

const form = document.querySelector("#cep-form");
const cepInput = document.querySelector("#cep-input");
const statusBox = document.querySelector("#status");
const addressResult = document.querySelector("#address-result");
const historyList = document.querySelector("#history-list");
const clearHistoryButton = document.querySelector("#clear-history");

const addressFields = [
  ["cep", "CEP"],
  ["logradouro", "Logradouro"],
  ["bairro", "Bairro"],
  ["localidade", "Cidade"],
  ["uf", "UF"],
  ["estado", "Estado"],
  ["regiao", "Regiao"],
  ["ddd", "DDD"],
  ["complemento", "Complemento"],
];

function onlyDigits(value) {
  return value.replace(/\D/g, "");
}

function formatCep(value) {
  const digits = onlyDigits(value).slice(0, 8);
  if (digits.length <= 5) {
    return digits;
  }
  return `${digits.slice(0, 5)}-${digits.slice(5)}`;
}

function setStatus(message, type) {
  statusBox.textContent = message;
  statusBox.className = `status visible ${type}`;
}

function clearStatus() {
  statusBox.textContent = "";
  statusBox.className = "status";
}

function renderAddress(address) {
  addressResult.innerHTML = addressFields
    .map(([key, label]) => {
      const value = address[key] || "-";
      return `
        <div>
          <dt>${label}</dt>
          <dd>${value}</dd>
        </div>
      `;
    })
    .join("");
}

function renderHistory(items) {
  if (!items.length) {
    historyList.innerHTML = '<div class="empty-state">O historico aparecera aqui.</div>';
    return;
  }

  historyList.innerHTML = items
    .map(
      (item) => `
        <article class="history-item">
          <div>
            <div class="history-title">${item.cep || "-"}</div>
            <div class="history-meta">${item.logradouro || "Endereco sem logradouro"} - ${item.localidade || "-"} / ${item.uf || "-"}</div>
          </div>
          <button class="delete-button" type="button" data-id="${item.id}">Excluir</button>
        </article>
      `,
    )
    .join("");
}

async function requestApi(path, options = {}) {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    headers: {
      Accept: "application/json",
      ...options.headers,
    },
    ...options,
  });

  if (!response.ok) {
    const message = response.status === 404
      ? "Registro nao encontrado."
      : "Nao foi possivel concluir a solicitacao.";
    throw new Error(message);
  }

  if (response.status === 204) {
    return null;
  }

  return response.json();
}

async function loadHistory() {
  const history = await requestApi("/cep/historico");
  renderHistory(history);
}

form.addEventListener("submit", async (event) => {
  event.preventDefault();
  clearStatus();

  const cep = onlyDigits(cepInput.value);
  if (cep.length !== 8) {
    setStatus("Informe um CEP com exatamente 8 numeros.", "error");
    return;
  }

  const submitButton = form.querySelector("button");
  submitButton.disabled = true;
  submitButton.textContent = "Buscando...";

  try {
    const address = await requestApi(`/cep/${cep}`);
    renderAddress(address);
    await loadHistory();
    setStatus("CEP consultado com sucesso.", "success");
  } catch (error) {
    setStatus(error.message, "error");
  } finally {
    submitButton.disabled = false;
    submitButton.textContent = "Buscar";
  }
});

cepInput.addEventListener("input", () => {
  cepInput.value = formatCep(cepInput.value);
});

historyList.addEventListener("click", async (event) => {
  const button = event.target.closest("[data-id]");
  if (!button) {
    return;
  }

  try {
    await requestApi(`/cep/historico/${button.dataset.id}`, { method: "DELETE" });
    await loadHistory();
    setStatus("Registro removido do historico.", "success");
  } catch (error) {
    setStatus(error.message, "error");
  }
});

clearHistoryButton.addEventListener("click", async () => {
  try {
    await requestApi("/cep/historico", { method: "DELETE" });
    await loadHistory();
    setStatus("Historico limpo com sucesso.", "success");
  } catch (error) {
    setStatus(error.message, "error");
  }
});

loadHistory().catch(() => {
  setStatus("Inicie o backend em http://localhost:8080 para usar o frontend.", "error");
});
