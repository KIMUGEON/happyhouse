<template>
  <div>
    <b-container
      v-if="houses && houses.length != 0"
      class="bv-example-row mt-3"
    >
      <div>
        <b-table
          class="aa"
          :items="interdata"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          id="my-table"
          @row-clicked="move"
        >
          <template #cell(actions)="row">
            <b-button
              size="sm"
              @click="info(row.item, row.index, $event.target)"
              class="mr-1"
              variant="outline-primary"
            >
              Info modal
            </b-button>
          </template>
        </b-table>

        <b-modal
          :id="infoModal.id"
          :title="infoModal.title"
          ok-only
          @hide="resetInfoModal"
        >
          <pre>{{ infoModal.content }}</pre>
        </b-modal>
        <b-pagination
          page-class="customPage"
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
        ></b-pagination>
        <!-- <b-table
          id="my-table"
          :items="item"
          :per-page="perPage"
          :current-page="currentPage"
          small
        ></b-table> -->
        <p class="mt-3" align="center">Total Count: {{ interest.length }}</p>
      </div>
      <!-- <house-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
        :page="1"
      /> -->
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
    {{ xcode }},{{ ycode }}
  </div>
</template>

<script>
// import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapActions, mapMutations, mapGetters } from "vuex";
const houseStore = "houseStore";
// const convert = require("xml-js");
export default {
  name: "HouseList",
  components: {
    // HouseListItem,
  },
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      interdata: null,
      markers: [],
      xcode: null,
      ycode: null,
      fields: [
        {
          key: "도로명",
          label: "도로명",
        },
        {
          key: "법정동",
          label: "법정동",
        },
        {
          key: "지번",
          label: "지번",
        },
        {
          key: "아파트",
          label: "아파트명",
        },
        {
          key: "거래금액",
          label: "거래금액",
        },
      ],
      infoModal: {
        id: "info-modal",
        title: "",
        content: "",
      },
    };
  },
  created() {
    this.interdata = this.interest;
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a3389d6e53891e1cd3c1daf7b1ffc340&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    async move(e) {
      console.log(e);
      var xy = e.법정동 + " " + e.지번;
      console.log(xy);
      var geocoder = new kakao.maps.services.Geocoder();

      await geocoder.addressSearch(xy, this.callback);

      this.xcode = this.fuckxcode;
      this.ycode = this.fuckycode;
      console.log(this.xcode, this.ycode);

      var moveLatLon = new kakao.maps.LatLng(this.xcode, this.ycode);
      this.map.setCenter(moveLatLon);
      var moveLatLng = new kakao.maps.LatLng(this.xcode, this.ycode);
      this.map.panTo(moveLatLng);
    },
    callback(result, status) {
      // console.log(this.xcode);
      if (status === kakao.maps.services.Status.OK) {
        console.log(result);
        console.log(result[0].y + result[0].x);
        this.xcode = result[0]["y"];
        this.ycode = result[0]["x"];
        console.log(this.xcode);
      }
    },

    colorChange(flag) {
      this.isColor = flag;
    },
    info(item, index, button) {
      this.infoModal.title = `Row index: ${index}`;
      this.infoModal.content = JSON.stringify(item, null, 2);
      this.$root.$emit("bv::show::modal", this.infoModal.id, button);
    },
    resetInfoModal() {
      this.infoModal.title = "";
      this.infoModal.content = "";
    },
    setting() {
      this.interdata = this.interest;
      console.log(this.interdata);
      console.log(typeof this.interdata);
      console.log(typeof this.interest);
      console.log(JSON.stringify(this.interest));

      console.log(JSON.parse(this.interest));
      // console.log(JSON.parse(this.interest[0]));

      console.log(JSON.stringify(this.interest[0]));
    },
    check(e) {
      this.interdata = e;
      this.$store.commit("houseStore/SET_INTEREST", e);
      console.log(this.interest);
      console.log(this.interdata);
    },
    // linkGen(pageNum) {
    // console.log(this.pageNo);
    // this.SET_PAGE_NO(pageNum);
    // this.pageNo = pageNum;
    // return pageNum === 1 ? "?" : (this.pageNo = this.pageNum);
    // this.$store.dispatch("getHouse", this.house);
    //  return pageNum === 1 ? '?' : `?page=${pageNum}`;
    // },
  },
  computed: {
    fuckxcode: function () {
      console.log(this.xcode);
      return this.xcode;
    },
    fuckycode: function () {
      console.log(this.ycode);
      return this.ycode;
    },
    ...mapState(houseStore, [
      "house",
      "houses",
      "totalCount",
      "pageNo",
      "interest",
    ]),
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapMutations(houseStore, ["SET_PAGE_NO", "SET_INTEREST"]),
    ...mapGetters(houseStore, ["reginterest"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
    rows() {
      // console.log(this.houses);
      console.log(JSON.stringify(this.interest));
      // console.log(sessionStorage.getItem("vuex"));
      // console.log(this.houses[1].지번);
      // console.log(this.apts);
      console.log(this.interest.length);
      return this.interest.length;
    },
  },
};
</script>

<style>
.aa > tbody :hover {
  background-color: lightblue;
}
.customPage.page-item.active .page-link {
  background-color: rgb(173, 180, 183);
  border-color: rgb(173, 180, 183);
  color: rgb(0, 0, 0);
}
</style>
