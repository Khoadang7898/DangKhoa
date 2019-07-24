import React from "react";
import { Root } from "native-base";
// import { StackNavigator, DrawerNavigator } from "react-navigation";
import { createDrawerNavigator, createStackNavigator, createAppContainer } from "react-navigation";


import BadgeFooter from "./screens/footer/badgeFooter";


import Home from "./screens/home/";

import SideBar from "./screens/sidebar";
import Service from "./screens/footer/Service";
import Paypal from "./screens/footer/paypal"
const Drawer = createDrawerNavigator(
  {
    Home: { screen: Home },
    BadgeFooter: { screen: BadgeFooter },
    Paypal : {screen:Paypal},
    Service: {screen:Service}
  },
  {
    initialRouteName: "Home",
    contentOptions: {
      activeTintColor: "#e91e63"
    },
    contentComponent: props => <SideBar {...props} />
  }
);

const AppNavigator = createStackNavigator(
  {
    Drawer: { screen: Drawer }
  },
  {
    initialRouteName: "Drawer",
    headerMode: "none"
  }
);

const AppContainer = createAppContainer(AppNavigator);

export default () =>
  <Root>
    <AppContainer />
  </Root>;
