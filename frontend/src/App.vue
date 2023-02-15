<template>
  <div>
    <nav>
      <header-view />
      <router-view />
      <footer-view />
    </nav>
  </div>
</template>
<script>
import HeaderView from "@/components/common/HeaderView.vue";
import FooterView from "@/components/common/FooterView.vue";

export default {
  name: "App",
  components: {
    HeaderView,
    FooterView,
  },
};
</script>
