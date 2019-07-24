import React, { Component } from "react";
import { StyleSheet} from "react-native";
import {
  Container,
  Header,
  Title,
  Content,
  Button,

  Left,
  Right,
  Body,
  Text
} from "native-base";
import Icon from "../IconE"
//import styles from "./styles";

class HeaderApp extends Component {
  render() {
    return (
        <Header style={{backgroundColor:"#00A247"}}>
          <Left>
          { this.props.back===true?  <Button
              transparent
              onPress={() => this.props.onBack()}
            >
                 <Icon
            name={"ios-arrow-back"}
            type={"ionicons"}
            style={styles.leftIcon}
          />
           {/* onPress={() => this.props.navigation.goBack()} */}
            </Button>: <Button
              transparent
              onPress={() => this.props.openmenu()}
            >
                 <Icon
            name={"menu"}
            type={"MaterialCommunityIcons"}
            style={styles.leftIcon}
          />
           {/* onPress={() => this.props.navigation.goBack()} */}
            </Button>}
           
          
          </Left>
          <Body/>
          <Right>
            <Button transparent>
              {/* <Icon name='search' /> */}
              <Icon
              name={"magnify"}
              type={"MaterialCommunityIcons"}
              style={styles.rightIcon1}
              
            />
            </Button>
            <Button transparent>
              {/* <Icon name='facebook-messenger' /> */}
              <Icon
              name={"facebook-messenger"}
              type={"MaterialCommunityIcons"}
              style={styles.rightIcon3}
            />
            </Button>
            <Button transparent>
            <Icon
              name={"phone"}
              type={"MaterialCommunityIcons"}
              style={styles.rightIcon2}
            />
            </Button>
            <Button transparent>
              {/* <Icon name='more' /> */}
              <Icon
              name={"dots-vertical"}
              type={"MaterialCommunityIcons"}
              style={styles.rightIcon4}
            />
            </Button>
          </Right>
        </Header>
    );
  }
}

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: "#3F51B5",

  },
  leftIconButton: {
    top: 5,
    left: 5,
    position: "absolute",
    padding: 11
  },
  leftIcon: {
    backgroundColor: "transparent",
    color: "#FFFFFF",
    
    fontSize: 24
  },
  textWrapper: {
    left: 71,
    position: "absolute"
  },
  title: {
    backgroundColor: "transparent",
    color: "#FFFFFF",
    fontSize: 18,
   
    fontWeight: "600",
    lineHeight: 18
  },
  rightIconsWrapper: {
    top: 5,
    position: "absolute",
    flexDirection: "row",
    alignItems: "center",
    right: 5
  },
  iconButton: {
    padding: 11
  },
  rightIcon1: {
    backgroundColor: "transparent",
    color: "#FFFFFF",
    
    fontSize: 24
  },
  rightIcon2: {
    backgroundColor: "transparent",
    color: "#FFFFFF",
    
    fontSize: 24
  },
  rightIcon3: {
    backgroundColor: "transparent",
    color: "#FFFFFF",
    
    fontSize: 24
  },
  rightIcon4: {
    backgroundColor: "transparent",
    color: "#FFFFFF",
    
    fontSize: 24
  }
});

export default HeaderApp;
