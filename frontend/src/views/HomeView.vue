<template>
  <div class="home">
    <div>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <!-- Text slides with image -->

        <b-carousel-slide
          caption="Today News"
          img-src="https://picsum.photos/1920/500/?image=1033"
        >
          <router-link
            :to="{ name: 'news' }"
            tag="li"
            active-class="sale-noti"
            exact
            ><h5 style="text-decoration: underline">View</h5></router-link
          ><br />
          <p style="color: white">
            오늘의 뉴스 토픽을 확인할 수 있습니다
          </p></b-carousel-slide
        >

        <!-- Slides with custom text -->
        <b-carousel-slide
          caption="Q&A Board"
          img-src="https://picsum.photos/1920/500/?image=520"
        >
          <router-link
            :to="{ name: 'qna' }"
            tag="li"
            active-class="sale-noti"
            exact
            ><h5 style="text-decoration: underline">View</h5></router-link
          ><br />
          <p style="color: white">부동산에 대한 다양한 질문을 남겨주세요</p>
        </b-carousel-slide>

        <!-- Slides with image only -->
        <b-carousel-slide
          caption="House Deal"
          img-src="https://picsum.photos/1920/500/?image=864"
        >
          <router-link
            :to="{ name: 'house' }"
            tag="li"
            active-class="sale-noti"
            exact
            ><h5 style="text-decoration: underline">View</h5></router-link
          ><br />
          <p style="color: white">아파트 거래 내역을 확인하실 수 있습니다</p>
        </b-carousel-slide>

        <!-- Slides with img slot -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->

        <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      </b-carousel>
    </div>

    <MiddleBanner />
  </div>
</template>

<script>
import MiddleBanner from "@/components/home/MiddleBanner.vue";

export default {
  name: "HomeView",
  components: {
    MiddleBanner,
  },
};
</script>
