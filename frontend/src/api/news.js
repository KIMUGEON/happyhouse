import { apiInstance } from "./index.js";

const api = apiInstance();

function listNews(success, fail) {
  api.get(`/news`).then(success).catch(fail);
}

function market_trend_listNews(success, fail) {
  api.get(`/news/market_trend`).then(success).catch(fail);
}

function policy_system_listNews(success, fail) {
  api.get(`/news/policy_system`).then(success).catch(fail);
}

function distribute_listNews(success, fail) {
  api.get(`/news/distribute`).then(success).catch(fail);
}

function industry_trend_listNews(success, fail) {
  api.get(`/news/industry_trend`).then(success).catch(fail);
}

export {
  listNews,
  market_trend_listNews,
  policy_system_listNews,
  distribute_listNews,
  industry_trend_listNews,
};
