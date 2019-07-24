import React, { Component } from "react";
import {
  Footer,
  Container,
  Body
} from "native-base";
import {  View ,ScrollView,Text} from 'react-native';
import styles from "./styles";
import HeaderApp from '../../../Header/HeaderApp';
import FooterApp from '../../../Footer/Footer';
import DetailP from "./DetailP"
// import ListItem from "../../ListItem"
class IndexDetailP extends Component {
  render() {
    return (
      <Container style={styles.container}>
        <HeaderApp back={true} onBack={() => this.props.navigation.goBack()} openmenu ={()=> this.props.navigation.openDrawer() } />
        <Body style={{backgroundColor:"#fff",width:"100%",height:"100%"}}>
  
           <DetailP style={{backgroundColor:"#fff", width:"100%",height:"100%"}}/>
           
        </Body>
       <FooterApp/>
      </Container>
    );
  }
}

export default IndexDetailP;
