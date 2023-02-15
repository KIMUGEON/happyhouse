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
      <b-button type="button" variant="outline-secondary" @click="displayCCTV">
        CCTV Info
      </b-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      markerPositions: [],
      cctvxy: [],
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
  computed: {
    ...mapState(houseStore, ["interest", "cctvs"]),
    ...mapActions(houseStore, ["getCctvList"]),
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.565419, 126.976718),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker() {
      var geocoder = new kakao.maps.services.Geocoder();

      // var callback = function (result, status) {
      //   if (status === kakao.maps.services.Status.OK) {
      //     console.log(result);
      //   }
      // };
      // console.log(geocoder.addressSearch("해남군 송지면", callback));
      // for (let j = 0; j < searchList.length; j++) {
      //   this.markers.setMap(null);
      // }
      // var geocoder = new kakao.maps.services.Geocoder();
      // console.log(markerPositions);
      // var callback = function (result, status) {
      //   if (status === kakao.maps.services.Status.OK) {
      //     console.log(result);
      //   }
      // };
      // if (this.markers.length > 0) {
      //   this.markers.forEach((marker) => marker.setMap(null));
      // }
      // for (let i = 0; i < this.interest.length; i++) {
      //   this.markerPositions[i] =
      //     this.interest[i].법정동 + this.interest[i].지번;
      //   geocoder.addressSearch(this.markerPositions[i], callback);
      //   console.log(geocoder.addressSearch(this.markerPositions[i], callback));
      // }

      // console.log(markerPositions);

      // const positions = markerPositions.map(
      //   (position) => new kakao.maps.LatLng(...position),
      // );
      // if (positions.length > 0) {
      //   this.markers = positions.map(
      //     (position) =>
      //       new kakao.maps.Marker({
      //         map: this.map,
      //         position,
      //       }),
      //   );

      //   const bounds = positions.reduce(
      //     (bounds, latlng) => bounds.extend(latlng),
      //     new kakao.maps.LatLngBounds(),
      //   );

      //   this.map.setBounds(bounds);
      // } 여기까지

      // var searchList = [];
      // var name = [];
      // for (let i = 0; i < this.interest.length; i++) {
      //   searchList[i] = this.interest[i].법정동 + this.interest[i].지번;
      //   name[i] = this.interest[i].아파트;
      //   console.log(searchList[i]);
      //   console.log(name[i]);
      // }
      // for (let j = 0; j < searchList.length; j++) {
      //   this.markerPositions = geocoder.addressSearch(
      //     searchList[j],
      //     (res, stat) => {
      //       if (stat === kakao.maps.services.Status.OK) {
      //         const coords = new kakao.maps.LatLng(res[0].y, res[0].x);
      //         const marker = new kakao.maps.Marker({
      //           map: this.map,
      //           position: coords,
      //         });
      //         const infowindow = new kakao.maps.InfoWindow({
      //           content: <div style="width:150px;text-align:center;padding:6px 0;">${name[j]}</div>,
      //         });
      //         infowindow.open(this.map, marker);
      //       }
      //     },
      //   );
      // }
      // if (this.markers.length > 0) {
      //   this.markers.forEach((marker) => marker.setMap(null));
      // }

      // const positions = markerPositions.map(
      //   (position) => new kakao.maps.LatLng(...position),
      // );

      // if (positions.length > 0) {
      //   this.markers = positions.map(
      //     (position) =>
      //       new kakao.maps.Marker({
      //         map: this.map,
      //         position,
      //       }),
      //   );

      //   const bounds = positions.reduce(
      //     (bounds, latlng) => bounds.extend(latlng),
      //     new kakao.maps.LatLngBounds(),
      //   );

      //   this.map.setBounds(bounds);
      // }
      // var marker = new kakao.maps.Marker({
      //   map: map,
      //   position: new kakao.maps.LatLng(33.450701, 126.570667),
      // });
      console.log(this.interest.length);
      var searchList = [];
      var name = [];
      for (let i = 0; i < this.interest.length; i++) {
        searchList[i] = this.interest[i].법정동 + this.interest[i].지번;
        name[i] = this.interest[i].아파트;
        console.log(searchList[i]);
        console.log(name[i]);
      }

      console.log(this.interest.length);
      // console.log(this.houses[0].법정동 + this.houses[0].지번);
      // var geocoder = new kakao.maps.services.Geocoder();
      // console.log(geocoder);
      // var callback = function (result, status) {
      //   if (status === kakao.maps.services.Status.OK) {
      //     console.log(result);
      //   }
      // };
      // geocoder.addressSearch(addr, callback);

      // const geocoder = new kakao.maps.services.Geocoder();

      for (let j = 0; j < searchList.length; j++) {
        geocoder.addressSearch(searchList[j], (res, stat) => {
          if (stat === kakao.maps.services.Status.OK) {
            const coords = new kakao.maps.LatLng(res[0].y, res[0].x);
            const marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
            });
            this.markers = marker[j];
            // marker[j].getMap();
            const infowindow = new kakao.maps.InfoWindow({
              content: (
                <div style="width:150px;text-align:center;padding:6px 0;">
                  ${name[j]}
                </div>
              ),
            });
            infowindow.open(this.map, marker[j]);
          }
        });
      }
      console.log(this.markers);
      this.getCctvList();
    },
    displayCCTV() {
      this.cctvxy = null;
      this.cctvxy = this.cctvs;
      console.log(this.cctvs);

      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png";
      // 마커이미지의 주소입니다

      var imageSize = new kakao.maps.Size(64, 69); // 마커이미지의 크기입니다
      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var imageOption = { offset: new kakao.maps.Point(27, 69) };
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption,
      );

      for (let i = 0; i < this.cctvxy[0].length; i++) {
        var markerPosition = new kakao.maps.LatLng(
          this.cctvxy[0][i].latitude._text,
          this.cctvxy[0][i].longitude._text,
          console.log(this.cctvxy[0][1].latitude._text),
          console.log(this.cctvxy[0][1].longitude._text),
        );

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          position: markerPosition,
          image: markerImage,
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(this.map);
        this.map.panTo(markerPosition);
      }
    },
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
