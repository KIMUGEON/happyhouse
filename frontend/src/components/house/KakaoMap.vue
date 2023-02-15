<template>
  <div>
    <div class="container" id="map"></div>
    <div class="button-group">
      <b-button
        type="button"
        variant="outline-secondary"
        @click="displayMarker"
      >
        Marker
      </b-button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null,
    };
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
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.565419, 126.976718),
        level: 5,
      };
      container.style.width = "1200px";
      container.style.height = "600px";
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker() {
      var addr = [];
      var aptname = [];
      for (var i = 0; i < this.houses.length; i++) {
        addr[i] = this.houses[i].법정동 + this.houses[i].지번;
        aptname[i] = this.houses[i].아파트;
      }
      console.log(addr);
      console.log(aptname);
      console.log(this.houses.length);
      // console.log(this.houses[0].법정동 + this.houses[0].지번);
      // var geocoder = new kakao.maps.services.Geocoder();
      // console.log(geocoder);
      // var callback = function (result, status) {
      //   if (status === kakao.maps.services.Status.OK) {
      //     console.log(result);
      //   }
      // };
      // geocoder.addressSearch(addr, callback);
      const geocoder = new kakao.maps.services.Geocoder();

      for (let j = 0; j < addr.length; j++) {
        geocoder.addressSearch(addr[j], (res, stat) => {
          if (stat === kakao.maps.services.Status.OK) {
            const coords = new kakao.maps.LatLng(res[0].y, res[0].x);
            const marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
            });
            const infowindow = new kakao.maps.InfoWindow({
              content: `<div style="width:150px;text-align:center;padding:6px 0;">${aptname[j]}</div>`,
            });
            infowindow.open(this.map, marker);
            console.log(aptname[j]);
            console.log(addr.length);
            console.log(j);
          }
        });
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">info window</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
};
</script>

<style scoped>
#map {
  width: 400px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
