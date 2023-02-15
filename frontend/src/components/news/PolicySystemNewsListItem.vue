<template>
  <b-tr
    ><a :href="'https://land.naver.com/' + url">
      <b-td> <b-img-lazy :src="photo" /></b-td>
      <b-td style="color: black">{{ title }}</b-td>
    </a></b-tr
  >
</template>

<script>
import { url } from "vuelidate/lib/validators";
export default {
  name: "PolicySystemNewsListItem",
  props: {
    url: url,
    photo: url,
    title: String,
  },
};
</script>

<style></style>
