import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

function jointUser(user, success, fail) {
  api.post(`/user/regist`, JSON.stringify(user)).then(success).catch(fail);
}

function getUser(user_id, success, fail) {
  api.get(`/user/${user_id}`).then(success).catch(fail);
}

function modifyUser(user, success, fail) {
  api
    .put(`/user/modify/${user.user_id}`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function deleteUser(user_id, success, fail) {
  api.delete(`/user/mypage/${user_id}`).then(success).catch(fail);
}

function listUser(param, success, fail) {
  api.get(`/user/list`, { params: param }).then(success).catch(fail);
}

function listdeleteUser(user_id, success, fail) {
  api.delete(`/user/list/${user_id}`).then(success).catch(fail);
}

// function logout(success, fail)

export {
  login,
  findById,
  jointUser,
  getUser,
  modifyUser,
  deleteUser,
  listUser,
  listdeleteUser,
};
