import React, { Component } from "react";
import { Container, Header, Content, Icon, Accordion, Text, View } from "native-base";


export default class Notification extends Component {
  constructor(){
    super();
    this.state ={
      dataArray : [
        { title: "Thông báo 1", content: "Nội dung thông báo : 1" },
        { title: "Thông báo 2", content: "Nội dung thông báo : 2" },
        { title: "Thông báo 3", content: "Nội dung thông báo : 3" }
      ]
    }
  }
  _renderHeader(item, expanded) {
    return (
      <View style={{
        flexDirection: "row",
        padding: 10,
        justifyContent: "space-between",
        alignItems: "center" ,
        backgroundColor: "#A9DAD6" }}>
      <Text style={{ fontWeight: "600" }}>
          {" "}{item.title}
        </Text>
        {expanded
          ? <Icon style={{ fontSize: 18 }} name="remove-circle" />
          : <Icon style={{ fontSize: 18 }} name="add-circle" />}
      </View>
    );
  }
  _renderContent(item) {
    return (
      <Text
        style={{
          backgroundColor: "#e3f1f1",
          padding: 10,
          fontStyle: "italic",
        }}
      >
        {item.content}
      </Text>
    );
  }
  render() {
    
    return (
          <Accordion
            dataArray={this.state.dataArray}
            animation={true}
            expanded={true}
            renderHeader={this._renderHeader}
            renderContent={this._renderContent}
          />
    );
  }
}
<br/>
