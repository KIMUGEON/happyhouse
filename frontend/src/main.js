import Vue from "vue";
import App from "./App.vue";
import router from "./router.js";
import store from "./store";

import moment from "moment";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

Vue.filter("dateFormat", (value) => {
  return moment(value, "YYYYMMDD").format("YYYY년 MM월 DD일");
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
