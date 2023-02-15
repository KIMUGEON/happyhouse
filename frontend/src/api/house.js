import { apiInstance, cctvInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();
const cctv = cctvInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

function cctvList(params, success, fail) {
  cctv.get(``, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, houseList, cctvList, dongList };
