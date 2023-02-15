<template>
  <section class="banner2 bg5 p-t-55 p-b-55">
    <div class="container">
      <div class="sec-title p-b-52">
        <h3 class="m-text5 t-center">News</h3>
      </div>

      <div class="row"></div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-10 col-md-8 col-lg-6 m-l-r-auto p-t-15 p-b-15">
          <router-link
            :to="{ name: 'news' }"
            tag="li"
            active-class="sale-noti"
            exact
          >
            <div class="hov-img-zoom pos-relative">
              <img
                src="https://picsum.photos/720/780/?image=2"
                alt="IMG-BANNER"
              />

              <div class="ab-t-l sizefull flex-col-c-m p-l-15 p-r-15">
                <span class="m-text9 p-t-45 fs-20-sm"> Today </span>

                <h3 class="l-text1 fs-35-sm">News</h3>

                <a href="/" class="s-text4 hov2 p-t-20"> View </a>
              </div>
            </div>
          </router-link>
        </div>
        <div class="col-sm-10 col-md-8 col-lg-6 m-l-r-auto p-t-15 p-b-15">
          <b-card class="overflow-auto">
            <b-low
              ><p style="font-weight: bold">
                <b-icon icon="newspaper" /> 주요 뉴스
              </p></b-low
            >
            <b-row>
              <b-col v-if="articles.length">
                <b-table-simple hover responsive>
                  <tbody>
                    <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                    <news-list-item
                      v-for="article in articles"
                      :key="article.title"
                      v-bind="article"
                    />
                  </tbody>
                </b-table-simple>
              </b-col>
              <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
            </b-row>
          </b-card>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
// import http from "@/api/http";
import { listNews } from "@/api/news.js";
import NewsListItem from "@/components/news/NewsListItem";

import Vue from "vue";

import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue";
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

export default {
  name: "NewsList",
  components: {
    NewsListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    listNews(
      (response) => {
        this.articles = response.data;
        console.log(response.data);
      },
      (error) => {
        console.log(error);
      },
    );
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
