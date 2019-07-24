import React, { Component } from "react";
import {
  StyleSheet,
  View,
  ScrollView,
  Image,
  Text,
  TouchableOpacity
} from "react-native";

import NameList from "./NameList"
import Item from "./Item"

export default class ListItem extends Component {
  render() {
    return (
    <View style={{width:"100%", height:350, margin:10}}>
        <NameList style={{height:30 }} />
        <ScrollView style={{height:280}} horizontal={true} >
        <Item style={{width:"50%"}} onDetail={()=>this.props.onDetail()}/>
        <Item style={{width:"50%"}} onDetail={()=>this.props.onDetail()}/>
        <Item style={{width:"50%"}} onDetail={()=>this.props.onDetail()}/>
        </ScrollView>
   </View>
    );
  }
}

