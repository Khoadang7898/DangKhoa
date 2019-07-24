import React, { Component } from "react";
import { StyleSheet, View, Image, Text,TouchableOpacity } from "react-native";

export default class NameList extends Component {
  render() {
    return (
      <TouchableOpacity style={[styles.root, this.props.style]}>
        <Image
          source={require("../../../../assets/ThitBoNhapKhauLogo.jpg")}
          style={styles.leftImage}
        />
        <Text style={styles.chipText}>Name list</Text>
      </TouchableOpacity>
    );
  }
}

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: "rgb(230,230,230)",
    flexDirection: "row",
    alignItems: "center",
    borderRadius: 50,
    maxHeight:30
  },
  leftImage: {
    width: 32,
    height: 32,
    backgroundColor: "#CCC",
    borderRadius: 16
  },
  chipText: {

    fontSize: 13
  }
});
