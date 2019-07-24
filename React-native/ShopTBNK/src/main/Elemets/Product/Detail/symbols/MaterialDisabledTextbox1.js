import React, { Component } from "react";
import { StyleSheet, View, TextInput } from "react-native";
import Icon from "../../../IconE";

export default class MaterialDisabledTextbox1 extends Component {
  render() {
    return (
      <View style={[styles.root, this.props.style]}>
        <TextInput
          placeholder={"Disabled Textbox"}
          editable={false}
          style={styles.inputStyle}
        />
        <Icon
          name={"information-outline"}
          type={"MaterialCommunityIcons"}
          style={styles.iconStyle}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: "transparent",
    flexDirection: "row",
    alignItems: "center",
    borderColor: "#D9D5DC",
    borderBottomWidth: 1
  },
  inputStyle: {
    flex: 1,
    color: "#000",
    alignSelf: "stretch",
    paddingTop: 16,
    paddingRight: 5,
    paddingBottom: 8,
    fontSize: 16,
    fontFamily: "roboto-regular",
    lineHeight: 16
  },
  iconStyle: {
    color: "#384850",
    fontFamily: "roboto-regular",
    fontSize: 24,
    paddingRight: 8,
    opacity: 0.7
  }
});
