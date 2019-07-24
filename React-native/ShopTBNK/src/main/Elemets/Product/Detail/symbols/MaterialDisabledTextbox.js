import React, { Component } from "react";
import { StyleSheet, View, TextInput } from "react-native";
import Icon from "../../../IconE";
import {  Item, Picker } from 'native-base';
export default class MaterialDisabledTextbox extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selected2: undefined
    };
  }
  onValueChange2(value: string) {
    this.setState({
      selected2: value
    });
  }
  render() {
    return (
      <View style={[styles.root, this.props.style]}>
        {/* <TextInput
          placeholder={"Disabled Textbox"}
          editable={false}
          style={styles.inputStyle}
        />
        <Icon
          name={"information-outline"}
          type={"MaterialCommunityIcons"}
          style={styles.iconStyle}
        /> */}
        <Item picker  style={styles.inputStyle}>
              <Picker
                mode="dropdown"
                style={{ width: undefined }}
                title="Tỉnh"
                placeholder="Tỉnh/Thành phố"
                placeholderStyle={{ color: "#bfc6ea" }}
                placeholderIconColor="#007aff"
                selectedValue={this.state.selected2}
                onValueChange={this.onValueChange2.bind(this)}
              >
                <Picker.Item label="Wallet" value="key0" />
                <Picker.Item label="ATM Card" value="key1" />
                <Picker.Item label="Debit Card" value="key2" />
                <Picker.Item label="Credit Card" value="key3" />
                <Picker.Item label="Net Banking" value="key4" />
              </Picker>
            </Item>
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
