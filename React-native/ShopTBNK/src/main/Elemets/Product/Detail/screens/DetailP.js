import React, { Component } from "react";
import {
  StyleSheet,
  View,
  Text,
  Image,
  TouchableOpacity,
  ScrollView
} from "react-native";
import MaterialDisabledTextbox from "../symbols/MaterialDisabledTextbox";
import MaterialDisabledTextbox1 from "../symbols/MaterialDisabledTextbox1";
import Icon from "../../../IconE";
import ListItem from "../../ListItem"
export default class DetailP extends Component {
  render() {
    return (
      <ScrollView style={styles.root} contentContainerStyle={{ width: "100%",
        height: 1200}}>
        <View style={{flex:1}}>
        <Text style={styles.text}>Thịt bò nhập khẩu</Text>
          <Image
            source={require("../assets/images/Ba-Chi-Bo-My-62.jpg")}
            resizeMode={"contain"}
            style={styles.image}
          />
        <Text style={styles.text2}>Giá :</Text>
        <Text style={styles.text3}>2000000</Text>
        <MaterialDisabledTextbox style={styles.materialDisabledTextbox} />
        <MaterialDisabledTextbox style={styles.materialDisabledTextbox1} />
        <Text style={styles.text4}>Tình trạng :</Text>
        <Text style={styles.text5}>Còn hàng</Text>
        <Text style={styles.text6}>Số lượng còn :</Text>
        <Text style={styles.text7}>20 kg</Text>
        <TouchableOpacity style={styles.button} />
        <Icon
          type={"MaterialCommunityIcons"}
          name={"cart-plus"}
          style={styles.icon}
        />
        <Text style={styles.text8}>Thêm</Text>
        <TouchableOpacity style={styles.button2} />
        <Text style={styles.text9}>Mua ngay</Text>
        <Text style={styles.text10}>Mô tả sản phẩm</Text>
        <Text style={styles.text11}>
          aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        </Text>
        <Text style={styles.text12}>Sản phẩm liên quan</Text>
        </View>
        <View style={{top:20,width:"100%"}}>
          <ListItem  />
        </View>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  root: {
    width: "100%",
    height: "100%",
    flex: 1,
    backgroundColor: "rgb(255,255,255)"
  },
  text: {
    top: 28.38,
    left: 53.38,
    color: "#121212",
    position: "absolute",
    fontSize: 20
  },
  image: {
    top: 68.22,
    width: 346.48,
    height: 200,
    position: "absolute"
  },
  text2: {
    top: 310.31,
    left: 53.38,
    color: "rgba(74,74,74,1)",
    position: "absolute",
    fontSize: 18
  },
  text3: {
    top: 310.31,
    left: 104.67,
    color: "rgba(206,20,20,1)",
    position: "absolute",
    fontSize: 18
  },
  materialDisabledTextbox: {
    top: 351.45,
    left: 53.38,
    width: 134.12,
    height: 37.98,
    position: "absolute"
  },
  materialDisabledTextbox1: {
    top: 351.45,
    left: 202.55,
    width: 149.1,
    height: 37.98,
    position: "absolute"
  },
  text4: {
    top: 419.67,
    left: 53.38,
    color: "#121212",
    position: "absolute"
  },
  text5: {
    top: 419.67,
    left: 202.55,
    color: "rgba(65,117,5,1)",
    position: "absolute"
  },
  text6: {
    top: 456.81,
    left: 53.38,
    color: "#121212",
    position: "absolute"
  },
  text7: {
    top: 456.81,
    left: 202.55,
    color: "rgba(176,25,25,1)",
    position: "absolute"
  },
  button: {
    top: 525.03,
    left: 43.47,
    width: 122.4,
    height: 39.13,
    backgroundColor: "rgba(25,181,55,1)",
    position: "absolute",
    opacity: 1,
    borderColor: "#000000",
    borderWidth: 0,
    shadowOpacity: 1
  },
  icon: {
    top: 528.59,
    left: 120.18,
    position: "absolute",
    color: "rgba(255,255,255,1)",
    fontSize: 32
  },
  text8: {
    top: 535.59,
    left: 58.38,
    color: "rgba(255,255,255,1)",
    position: "absolute",
    fontSize: 18
  },
  button2: {
    top: 526.02,
    left: 206.97,
    width: 110.36,
    height: 38.13,
    backgroundColor: "rgba(202,28,28,1)",
    position: "absolute",
    opacity: 1,
    borderColor: "#000000",
    borderWidth: 0,
    shadowOpacity: 1
  },
  text9: {
    top: 535.59,
    left: 225.21,
    color: "rgba(255,255,255,1)",
    position: "absolute",
    fontSize: 18
  },
  text10: {
    top: 608.29,
    left: 47.45,
    color: "#121212",
    position: "absolute"
  },
  text11: {
    top: 644.95,
    left: 51.18,
    width: 263.87,
    height: 119.39,
    color: "#121212",
    position: "absolute"
  },
  scrollArea: {
    top: 822.14,
    left: 26.29,
    width: 325.36,
    height: 171.56,
    backgroundColor: "rgba(230, 230, 230,1)",
    position: "absolute"
  },
  scrollArea_contentContainerStyle: {},
  text12: {
    top: 791.89,
    left: 50.46,
    width: 138.46,
    height: 22.07,
    color: "#121212",
    position: "absolute"
  }
});
