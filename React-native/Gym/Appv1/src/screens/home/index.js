import React, { Component } from "react";
import { Image, View, StatusBar,ActivityIndicator } from "react-native";
import {
  Container, Button, H3, Text, Item,
  Label,
  Input,

  Form
} from "native-base";


import drawerImage from "../../../assets/logogym.png"

class Home extends Component {
  constructor() {
    super();
    this.state = {
      isLogin: -1,
      result:-1,
      username: '',
      password: '',
      data: {}
    }
  }
  login() {
    this.setState({
      isLogin:1
    })
    fetch('https://gymcenter.herokuapp.com/user/login', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: this.state.username,//result
        password: this.state.password,//maLogin
      })
    })
      .then((response) => response.json())
      .then((responseJson) => {
        //if(responseJson.result==1)
        this.setState({
          result: responseJson.result,
          data: responseJson,
          isLogin:-1
        })
        if (this.state.result == "1") {
          this.props.navigation.navigate('BadgeFooter', { data: this.state.data })
        }
        //return 1;
        //else return 0;
      })
      .catch((error) => {
        console.error(error);
      });
    return 0;
  }
  SetTex(text, type) {
    alph = /^[a-zA-Z]+$/
    var us = this.state.username
    var ps = this.state.password
    if (type == 'username') {
      this.setState({
        username: text,
        password: ps
      })

    }
    else {
      this.setState({
        username: us,
        password: text
      })
    }
  }
  render() {
    return (
      <Container style={{ backgroundColor: "#333333" }}>
        <StatusBar barStyle="light-content" />
        <View style={{   // backgroundColor : '#999999',
          flex: 1,
          width: null,
          height: null
        }}>
          <Image square style={{
            position: "absolute",
            width: 250,
            marginTop: 50,
            marginLeft: 60,
            height: 100,
            resizeMode: "cover"
          }} source={drawerImage} />
          <View style={{ display :(this.state.result===0?"flex":"none"), marginBottom: -100, marginTop: 180, marginLeft: 60 }}>
            <Text style={{color:"#FF0000"}} >
            Sai tên hoặc mật khẩu
            </Text>
          </View>
          <Form style={{ marginBottom: 80, marginTop: 150, marginLeft: 60 }}>
            <Item floatingLabel style={{ width: 200, height: 50, margin: 10 }}>
              <Label style={{ color: '#fff' }}>Tài khoản</Label>
              <Input onChangeText={(text) => this.SetTex(text, "username")}
                style={{ color: '#fff' }} />
            </Item>
            <Item floatingLabel last style={{ width: 200, height: 50, margin: 20 }}>
              <Label style={{ color: '#fff' }}>Mật khẩu</Label>
              <Input secureTextEntry onChangeText={(text) => this.SetTex(text, "password")}
                style={{ color: '#fff' }} />
            </Item>
          </Form>
          <View style={{ marginTop: 10 }}>
            <Button
              style={{ backgroundColor: "#000000", alignSelf: "center" }}
              onPress={() => this.login()}//this.props.navigation.navigate('BadgeFooter')}
            >
              <View style={{display : (this.state.isLogin===-1?"none":"flex")}}>
                <ActivityIndicator size="large" color="#0000ff" />
              </View>
              <View style={{display : (this.state.isLogin===-1?"flex":"none")}}>
                <Text> Đăng Nhập</Text>
              </View>
            </Button>
          </View>
        </View>
      </Container>
    );
  }
}

export default Home;
