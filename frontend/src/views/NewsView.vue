<template>
  <div>
    <section
      class="bg-title-page p-t-50 p-b-40 flex-col-c-m"
      :style="'background-image: url(https://picsum.photos/1920/239/?image=1033);'"
    >
      <h2 class="l-text2 t-center">News</h2>
      <p class="m-text13 t-center">Today News</p>
    </section>

    <section class="bgwhite p-t-55 p-b-65">
      <div class="container">
        <div class="row">
          <div class="col-sm-6 col-md-4 col-lg-3 p-b-50">
            <div class="leftbar p-r-20 p-r-0-sm">
              <!--  -->
              <div>
                <h4 class="m-text14 p-b-7">Categories</h4>

                <ul class="p-b-54">
                  <li class="p-t-4">
                    <a href="#" class="s-text13 active2" @click="toggleOnOff1">
                      <div id="1">주요 뉴스</div>
                    </a>
                  </li>

                  <li class="p-t-4">
                    <a href="#" class="s-text13" @click="toggleOnOff2">
                      <div id="2">시장 동향</div>
                    </a>
                  </li>

                  <li class="p-t-4">
                    <a href="#" class="s-text13" @click="toggleOnOff3">
                      <div id="3">정책/제도</div>
                    </a>
                  </li>

                  <li class="p-t-4">
                    <a href="#" class="s-text13" @click="toggleOnOff4">
                      <div id="4">분양</div>
                    </a>
                  </li>

                  <li class="p-t-4">
                    <a href="#" class="s-text13" @click="toggleOnOff5">
                      <div id="5">업계 동향</div>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-8 col-lg-9 p-b-50">
            <!-- Product -->
            <news-list v-if="isStatus1" />
            <market-trend-news-list v-if="isStatus2" />
            <policy-system-news-list v-if="isStatus3" />
            <distribute-news-list v-if="isStatus4" />
            <industry-trend-news-list v-if="isStatus5" />
            <!-- Pagination -->
            <div class="pagination flex-m flex-w p-t-26"></div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import { mapState } from "vuex";

import NewsList from "@/components/news/NewsList.vue";
import MarketTrendNewsList from "@/components/news/MarketTrendNewsList.vue";
import PolicySystemNewsList from "@/components/news/PolicySystemNewsList.vue";
import DistributeNewsList from "@/components/news/DistributeNewsList.vue";
import IndustryTrendNewsList from "@/components/news/IndustryTrendNewsList.vue";
export default {
  data() {
    return {
      isStatus1: true,
      isStatus2: false,
      isStatus3: false,
      isStatus4: false,
      isStatus5: false,
    };
  },
  computed: {
    ...mapState("product", {
      totalProducts: (state) => state.totalProducts,
      page: (state) => state.page,
    }),
  },
  methods: {
    toggleOnOff1: function () {
      this.isStatus1 = true;
      this.isStatus2 = false;
      this.isStatus3 = false;
      this.isStatus4 = false;
      this.isStatus5 = false;
      var x1 = document.getElementById("1");
      x1.style.color = "red";
      var x2 = document.getElementById("2");
      x2.style.color = "#888888";
      var x3 = document.getElementById("3");
      x3.style.color = "#888888";
      var x4 = document.getElementById("4");
      x4.style.color = "#888888";
      var x5 = document.getElementById("5");
      x5.style.color = "#888888";
    },
    toggleOnOff2: function () {
      this.isStatus1 = false;
      this.isStatus2 = true;
      this.isStatus3 = false;
      this.isStatus4 = false;
      this.isStatus5 = false;
      var x1 = document.getElementById("1");
      x1.style.color = "#888888";
      var x2 = document.getElementById("2");
      x2.style.color = "red";
      var x3 = document.getElementById("3");
      x3.style.color = "#888888";
      var x4 = document.getElementById("4");
      x4.style.color = "#888888";
      var x5 = document.getElementById("5");
      x5.style.color = "#888888";
    },
    toggleOnOff3: function () {
      this.isStatus1 = false;
      this.isStatus2 = false;
      this.isStatus3 = true;
      this.isStatus4 = false;
      this.isStatus5 = false;
      var x1 = document.getElementById("1");
      x1.style.color = "#888888";
      var x2 = document.getElementById("2");
      x2.style.color = "#888888";
      var x3 = document.getElementById("3");
      x3.style.color = "red";
      var x4 = document.getElementById("4");
      x4.style.color = "#888888";
      var x5 = document.getElementById("5");
      x5.style.color = "#888888";
    },
    toggleOnOff4: function () {
      this.isStatus1 = false;
      this.isStatus2 = false;
      this.isStatus3 = false;
      this.isStatus4 = true;
      this.isStatus5 = false;
      var x1 = document.getElementById("1");
      x1.style.color = "#888888";
      var x2 = document.getElementById("2");
      x2.style.color = "#888888";
      var x3 = document.getElementById("3");
      x3.style.color = "#888888";
      var x4 = document.getElementById("4");
      x4.style.color = "red";
      var x5 = document.getElementById("5");
      x5.style.color = "#888888";
    },
    toggleOnOff5: function () {
      this.isStatus1 = false;
      this.isStatus2 = false;
      this.isStatus3 = false;
      this.isStatus4 = false;
      this.isStatus5 = true;
      var x1 = document.getElementById("1");
      x1.style.color = "#888888";
      var x2 = document.getElementById("2");
      x2.style.color = "#888888";
      var x3 = document.getElementById("3");
      x3.style.color = "#888888";
      var x4 = document.getElementById("4");
      x4.style.color = "#888888";
      var x5 = document.getElementById("5");
      x5.style.color = "red";
    },
    changePage(page) {
      this.$store.dispatch("product/setProducts", page);
    },
  },
  components: {
    NewsList,
    MarketTrendNewsList,
    PolicySystemNewsList,
    DistributeNewsList,
    IndustryTrendNewsList,
  },
};
</script>
