import React, { Component } from "react";
import {   StyleSheet,
  View,
  Platform,
  Text,
  FlatList,
  TouchableOpacity,
  Image,
  Dimensions,} from "react-native";
import Icon from "../IconE";
import { colors, fonts } from '../../../styles';
export default class Item extends Component {
  render() {
    return (
      <TouchableOpacity  style={{padding:10}}  onPress={() => this.props.onDetail()}>
      <View style={styles.itemOneContainer}>
        <View style={styles.itemOneImageContainer}>
          <Image style={styles.itemOneImage} source={require('../../../../assets/Ba-Chi-Bo-My-6.jpg' )} />
        </View>
        <View style={styles.itemOneContent}>
          <Text style={styles.itemOneTitle} numberOfLines={1}>
            Name ---->
          </Text>
          <Text
            style={styles.itemOneSubTitle}
            styleName="collapsible"
            numberOfLines={3}
          >
            Mô tả :
          </Text>
          <Text style={styles.itemOnePrice} numberOfLines={1}>
            Giá: 
          </Text>
          <TouchableOpacity
                style={[
                  styles.badge,
                  { backgroundColor: colors.green },
                ]}
              >
                <Text
                  style={{ left:"40%",fontSize: 10, color: colors.white }}
                  styleName="bright"
                >
                  Mua
                </Text>
              </TouchableOpacity>
        </View>
      </View>
    </TouchableOpacity>
    );
  }
}

// const styles = StyleSheet.create({
//   root: {
//     width:200,

//     flex: 1,
//     backgroundColor: "#FFF",
//     flexWrap: "nowrap",
//     elevation: 3,
//     borderRadius: 2,
//     borderColor: "#CCC",
//     borderWidth: 1,
//     shadowOffset: {
//       height: 2,
//       width: -2
//     },
//     shadowColor: "#000",
//     shadowOpacity: 0.1,
//     shadowRadius: 1.5,
//     overflow: "hidden"
//   },
//   cardItemImagePlace: {
//     backgroundColor: "#ccc",
//     width: "100%",
//     height:"50%"
//   },
//   bodyContent: {
//     width: 180,
//     padding: 5
//   },
//   titleStyle: {
//     color: "#000",
//     fontSize: 13,
//     fontFamily: "roboto-regular"
//   },
//   subtitleStyle: {
// top:10,
//     flexDirection: 'row',
//     color: "#000",
//     fontSize: 11,
//     fontFamily: "roboto-regular",
//     lineHeight: 16
//   },
//   actionBody: {
//     width: 359,
//     flexDirection: "row",
//     padding: 8
//   },
//   actionButton1: {
//     height: 36,
//     padding: 8
//   },
//   actionText1: {
//     color: "#000",
//     opacity: 0.9,
//     fontSize: 14
//   },
//   actionButton2: {
//     height: 36,
//     padding: 8
//   },
//   actionText2: {
//     color: "#000",
//     opacity: 0.9,
//     fontSize: 14
//   },
//   actionButton3: {
//     top:15,
//     backgroundColor:"#00A247",
//     flexDirection: 'row',
//     height: 23,
//     borderRadius: 20,
//     width:"100%",
//     bottom:5,
//     alignItems:"center"
//   },
//   iconStyle: {
//     right: 0,
//     left:80,
//     fontSize: 24,
//     color: "#fff"
//   }
// });

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.white,
  },
  tabsContainer: {
    alignSelf: 'stretch',
    marginTop: 30,
  },
  itemOneContainer: {
    flex: 1,
    width: Dimensions.get('window').width / 2 - 40,
  },
  itemOneImageContainer: {
    borderRadius: 3,
    overflow: 'hidden',
  },
  itemOneImage: {
    height: 200,
    width: Dimensions.get('window').width / 2 - 40,
  },
  itemOneTitle: {
    fontFamily: fonts.primaryRegular,
    fontSize: 15,
  },
  itemOneSubTitle: {
    fontFamily: fonts.primaryRegular,
    fontSize: 13,
    color: '#B2B2B2',
    marginVertical: 3,
  },
  itemOnePrice: {
    fontFamily: fonts.primaryRegular,
    fontSize: 15,
  },
  itemOneRow: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 10,
  },
  itemOneContent: {
    marginTop: 5,
    marginBottom: 10,
  },
  itemTwoContainer: {
    paddingBottom: 10,
    backgroundColor: 'white',
    marginVertical: 5,
  },
  itemTwoContent: {
    padding: 20,
    position: 'relative',
    marginHorizontal: Platform.OS === 'ios' ? -15 : 0,
    height: 150,
  },
  itemTwoTitle: {
    color: colors.white,
    fontFamily: fonts.primaryBold,
    fontSize: 20,
  },
  itemTwoSubTitle: {
    color: colors.white,
    fontFamily: fonts.primaryRegular,
    fontSize: 15,
    marginVertical: 5,
  },
  itemTwoPrice: {
    color: colors.white,
    fontFamily: fonts.primaryBold,
    fontSize: 20,
  },
  itemTwoImage: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
  },
  itemTwoOverlay: {
    position: 'absolute',
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
    backgroundColor: '#6271da',
    opacity: 0.5,
  },
  itemThreeContainer: {
    backgroundColor: 'white',
  },
  itemThreeSubContainer: {
    flexDirection: 'row',
    paddingVertical: 10,
  },
  itemThreeImage: {
    height: 100,
    width: 100,
  },
  itemThreeContent: {
    flex: 1,
    paddingLeft: 15,
    justifyContent: 'space-between',
  },
  itemThreeBrand: {
    fontFamily: fonts.primaryRegular,
    fontSize: 14,
    color: '#617ae1',
  },
  itemThreeTitle: {
    fontFamily: fonts.primaryBold,
    fontSize: 16,
    color: '#5F5F5F',
  },
  itemThreeSubtitle: {
    fontFamily: fonts.primaryRegular,
    fontSize: 12,
    color: '#a4a4a4',
  },
  itemThreeMetaContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  itemThreePrice: {
    fontFamily: fonts.primaryRegular,
    fontSize: 15,
    color: '#5f5f5f',
    textAlign: 'right',
  },
  itemThreeHr: {
    flex: 1,
    height: 1,
    backgroundColor: '#e3e3e3',
    marginRight: -15,
  },
  badge: {
    top:8,
    backgroundColor: colors.secondary,
    borderRadius: 10,
    paddingHorizontal: 10,
    paddingVertical: 5,
  },
});
