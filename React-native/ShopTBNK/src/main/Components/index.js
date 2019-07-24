import React, { Component } from "react";
import {
  Footer,
  Container,
  Body
} from "native-base";
import {  View ,ScrollView,Text} from 'react-native';

import Item from "../Elemets/Product/Item"
import ListItem from "../Elemets/Product/ListItem"
import styles from "./styles";
import HeaderApp from '../Elemets/Header/HeaderApp';
import FooterApp from '../Elemets/Footer/Footer';
class Index extends Component {
  render() {
    return (
      <Container style={styles.container}>
        <HeaderApp  back={false} onBack={() => this.props.navigation.goBack()} openmenu ={()=> this.props.navigation.openDrawer() } />
        <Body style={{backgroundColor:"#fff"}}>
          <ScrollView style={{width:"100%",height:"100%"}}><ListItem  onDetail={()=> this.props.navigation.navigate("IndexDetailP")}/>
             <ListItem onDetail={()=> this.props.navigation.navigate("IndexDetailP")}/>
             <ListItem onDetail={()=> this.props.navigation.navigate("IndexDetailP")}/>
             <ListItem onDetail={()=> this.props.navigation.navigate("IndexDetailP")}/>
             <ListItem onDetail={()=> this.props.navigation.navigate("IndexDetailP")}/>
             <ListItem onDetail={()=> this.props.navigation.navigate("IndexDetailP")}/></ScrollView>
        </Body>
       <FooterApp/>
      </Container>
    );
  }
}

export default Index;
