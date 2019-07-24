import React, { Component } from "react";
import {
  Button,
  Footer,
  FooterTab,
  Text,
  Icon,
  Badge,
} from "native-base";

// import { StyleSheet, View, TouchableOpacity, Text } from "react-native";
// import Icon from "../IconE"
import { StyleSheet,TouchableOpacity} from "react-native";
class FooterApp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      tab1: false,
      tab2: false,
      tab3: true,
      tab4: false
    };
  }
  toggleTab1() {
    this.setState({
      tab1: true,
      tab2: false,
      tab3: false,
      tab4: false
    });
  }
  toggleTab2() {
    this.setState({
      tab1: false,
      tab2: true,
      tab3: false,
      tab4: false
    });
  }
  toggleTab3() {
    this.setState({
      tab1: false,
      tab2: false,
      tab3: true,
      tab4: false
    });
  }
  toggleTab4() {
    this.setState({
      tab1: false,
      tab2: false,
      tab3: false,
      tab4: true
    });
  }
  render() {
    return (
    

        <Footer style={{backgroundColor:"#00A247"}}>
          <FooterTab style={{backgroundColor:"#00A247"}}>
            <Button
 
              active={this.state.tab1}
              onPress={() => this.toggleTab1()}
              vertical
              badge
            >
              <Badge>
                <Text>2</Text>
              </Badge>
             <Icon active={this.state.tab1} type="MaterialCommunityIcons" name="home" />
              {/* <Icon
            name={"home"}
            type={"MaterialCommunityIcons"}
            style={styles.icon1}
            active={this.state.tab1} 
          > 
                <Text style={{backgroundColor:"#CC0000" , marginBottom:10}}>2</Text>
              </Icon> */}
              {/* <Text style={{fontSize:11,margin:0,padding:0}}>Home</Text> */}
            </Button>
            <Button active={this.state.tab2} 

            onPress={() => this.toggleTab2()}
            vertical
              badge
            >
              <Badge style={{ backgroundColor: "green" }}>
                <Text>51</Text>
              </Badge>
               <Icon active={this.state.tab2} name="cart" />
             
              {/* <Icon
            name={"cart-outline"}
            type={"MaterialCommunityIcons"}
            style={styles.icon1}
            active={this.state.tab2}
          /> */}
{/* 
              <Text style={{fontSize:11}}>Shopping Cart</Text> */}
            </Button>
            <Button

              active={this.state.tab3}
              onPress={() => this.toggleTab3()}
              vertical
              badge
            >
              <Badge style={{ backgroundColor: "green" }}>
                <Text>51</Text>
              </Badge>
              <Icon active={this.state.tab3} type="MaterialCommunityIcons" name="bell-ring-outline" />
              {/* <Icon
            name={"bell-ring-outline"}
            type={"MaterialCommunityIcons"}
            style={styles.icon1}
            active={this.state.tab3} 
          /> */}
              {/* <Text style={{fontSize:11,margin:0,padding:0}}>Notification</Text> */}
            </Button>
            <Button active={this.state.tab4} 

             onPress={() => this.toggleTab4()}
             vertical
              badge
             >
            <Badge style={{ backgroundColor: "green" }}>
                <Text>51</Text>
              </Badge>
              <Icon active={this.state.tab4} name="contact" />
              {/* <Icon
            name={"account"}
            type={"MaterialCommunityIcons"}
            style={styles.icon1}
            active={this.state.tab4}
          /> */}
              {/* <Text style={{fontSize:11,margin:0,padding:0}}>Account</Text> */}
            </Button>
          </FooterTab>
        </Footer>
    );
  }
}
const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: "rgba(255,255,255,1)",
    flexDirection: "row",
    alignItems: "center",
    elevation: 3,
    shadowOffset: {
      height: -2,
      width: 0
    },
    shadowColor: "#111",
    shadowOpacity: 0.2,
    shadowRadius: 1.2
  },
  btnWrapper1: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 9,
    paddingBottom: 9,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  icon1: {
    backgroundColor: "transparent",
    color: "rgba(242,15,15,1)",
    fontSize: 24,
    opacity: 0.8
  },
  btn1Text: {
    width: 69.66,
    height: 14,
    color: "rgba(196,31,31,1)",
    alignSelf: "flex-start",
    opacity: 0.8,
    fontFamily: "roboto-regular",
    letterSpacing: 0,
    textAlign: "center"
  },
  activebtnWrapper: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 6,
    paddingBottom: 10,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  activeIcon: {
    backgroundColor: "transparent",
    color: "rgba(13,11,11,1)",
    fontSize: 24,
    opacity: 1
  },
  activeText: {
    width: 92.15,
    height: 18,
    color: "rgba(0,0,0,1)",
    opacity: 1,
    paddingTop: 4,
    fontSize: 14,
    fontFamily: "roboto-regular"
  },
  btnWrapper2: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 8,
    paddingBottom: 6,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  icon2: {
    backgroundColor: "transparent",
    color: "rgba(0,0,0,1)",
    fontSize: 24,
    opacity: 0.8
  },
  btn2Text: {
    color: "rgba(0,0,0,1)",
    opacity: 0.8,
    fontFamily: "roboto-regular"
  },
  btnWrapper3: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 8,
    paddingBottom: 6,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  icon3: {
    backgroundColor: "transparent",
    color: "rgba(0,0,0,1)",
    fontSize: 24,
    opacity: 0.8
  },
  btn3Text: {
    color: "rgba(0,0,0,1)",
    opacity: 0.8,
    fontFamily: "roboto-regular"
  }
});


export default FooterApp;
// import React, { Component } from "react";
// import { StyleSheet, View, TouchableOpacity, Text } from "react-native";
// import Icon from "../IconE";

// export default class FooterApp extends Component {
//   render() {
//     return (
//       <View style={[styles.root, this.props.style]}>
//         <TouchableOpacity style={styles.btnWrapper1}>
//           <Icon
//             name={"home"}
//             type={"MaterialCommunityIcons"}
//             style={styles.icon1}
//           />
//           <Text style={styles.btn1Text}>Home</Text>
//         </TouchableOpacity>
//         <TouchableOpacity style={styles.activebtnWrapper}>
//           <Icon
//             name={"cart-outline"}
//             type={"MaterialCommunityIcons"}
//             style={styles.activeIcon}
//           />
//           <Text style={styles.activeText}>Shopping Cart</Text>
//         </TouchableOpacity>
//         <TouchableOpacity style={styles.btnWrapper2}>
//           <Icon
//             name={"bell-ring-outline"}
//             type={"MaterialCommunityIcons"}
//             style={styles.icon2}
//           />
//           <Text style={styles.btn2Text}>Notification</Text>
//         </TouchableOpacity>
//         <TouchableOpacity style={styles.btnWrapper3}>
//           <Icon
//             name={"account"}
//             type={"MaterialCommunityIcons"}
//             style={styles.icon3}
//           />
//           <Text style={styles.btn3Text}>Account</Text>
//         </TouchableOpacity>
//       </View>
//     );
//   }
// }

const styles1 = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: "rgba(255,255,255,1)",
    flexDirection: "row",
    alignItems: "center",
    elevation: 3,
    shadowOffset: {
      height: -2,
      width: 0
    },
    shadowColor: "#111",
    shadowOpacity: 0.2,
    shadowRadius: 1.2
  },
  btnWrapper1: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 9,
    paddingBottom: 9,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  icon1: {
    backgroundColor: "transparent",
    color: "rgba(242,15,15,1)",
    fontSize: 24,
    opacity: 0.8
  },
  btn1Text: {
    width: 69.66,
    height: 14,
    color: "rgba(196,31,31,1)",
    alignSelf: "flex-start",
    opacity: 0.8,
    fontFamily: "roboto-regular",
    letterSpacing: 0,
    textAlign: "center"
  },
  activebtnWrapper: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 6,
    paddingBottom: 10,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  activeIcon: {
    backgroundColor: "transparent",
    color: "rgba(13,11,11,1)",
    fontSize: 24,
    opacity: 1
  },
  activeText: {
    width: 92.15,
    height: 18,
    color: "rgba(0,0,0,1)",
    opacity: 1,
    paddingTop: 4,
    fontSize: 14,
    fontFamily: "roboto-regular"
  },
  btnWrapper2: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 8,
    paddingBottom: 6,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  icon2: {
    backgroundColor: "transparent",
    color: "rgba(0,0,0,1)",
    fontSize: 24,
    opacity: 0.8
  },
  btn2Text: {
    color: "rgba(0,0,0,1)",
    opacity: 0.8,
    fontFamily: "roboto-regular"
  },
  btnWrapper3: {
    flex: 0.25,
    flexDirection: "column",
    alignItems: "center",
    paddingTop: 8,
    paddingBottom: 6,
    minWidth: 80,
    maxWidth: 168,
    paddingHorizontal: 12
  },
  icon3: {
    backgroundColor: "transparent",
    color: "rgba(0,0,0,1)",
    fontSize: 24,
    opacity: 0.8
  },
  btn3Text: {
    color: "rgba(0,0,0,1)",
    opacity: 0.8,
    fontFamily: "roboto-regular"
  }
});