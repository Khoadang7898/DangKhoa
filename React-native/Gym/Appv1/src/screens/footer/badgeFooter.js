import React, { Component } from "react";
import {
  Container,
  Header,
  Title,
  Content,
  Button,
  Footer,
  FooterTab,
  Text,
  Body,
  Left,
  Right,
  Icon,
  Badge,
  View,
} from "native-base";
import TrainingSchedule from './TrainingSchedule'
import styles from "./styles";
import Notification from "./Notification"
import Service from './Service'
class BadgeFooter extends Component {
  constructor(props) {
    super(props);
    this.state = {
      pl: "flex",
      pl2: "none",
      pl3: "none",
      pl4: "none",
      tab1: true,
      tab2: false,
      tab3: false,
      tab4: false,
      text: "null",
      isChange: false,
      data: this.props.navigation.getParam('data')
    };
  }
  toggleTab1() {
    this.setState({
      pl: "flex",
      pl2: "none",
      pl3: "none",
      pl4: "none",
      tab1: true,
      tab2: false,
      tab3: false,
      tab4: false,
    });
  }
  toggleTab2() {
    this.setState({
      pl: "none",
      pl2: "flex",
      pl3: "none",
      pl4: "none",

      tab1: false,
      tab2: true,
      tab3: false,
      tab4: false

    });
  }
  onValueChange2(value) {
    this.setState({
      selected2: value
    });
  }
  toggleTab3() {
    this.setState({
      pl: "none",
      pl2: "none",
      pl3: "flex",
      pl4: "none",
      tab1: false,
      tab2: false,
      tab3: true,
      tab4: false
    });
  }
  toggleTab4() {
    this.setState({
      pl: "none",
      pl2: "none",
      pl3: "none",
      pl4: "flex",
      tab1: false,
      tab2: false,
      tab3: false,
      tab4: true,
    });
  }
  Upss=(date)=>
  {
    this.setState({
      isChange : date
    })
  }


  login() {
    fetch('https://gymcenter.herokuapp.com/data/login', {
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
          isLogin: responseJson.result,
          maLogin: responseJson
        })
        if (this.state.isLogin == "1") {
          this.props.navigation.navigate('BadgeFooter', { maLogin: this.state.maLogin })
        }
        //return 1;
        //else return 0;
      })
      .catch((error) => {
        console.error(error);
      });
    return 0;
  }

  render() {
    var da = this.props.navigation.getParam('data')
    return (
      <Container style={styles.container}>

        <Header style={{ backgroundColor: '#000000' }}>
          <Left>
            <Button
              transparent
              onPress={() => this.props.navigation.openDrawer()}
            >
              <Icon name="menu" />
            </Button>
          </Left>
          <Body>
            <Title>{this.state.data.result}</Title>
          </Body>
          <Right />
        </Header>

        <Content padder>
          {/* <View style={{ flex: 1, padding: 16, paddingTop: 30, backgroundColor: '#fff' }}>
        <Table borderStyle={{borderWidth: 2, borderColor: '#c8e1ff'}}>
          <Row data={this.state.tableHead} style={ { height: 40, backgroundColor: '#f1f8ff' }} textStyle={ { margin: 6 }}/>
          
          <Rows data={this.state.tableData} textStyle={ { margin: 6 }}/>
        </Table>
      </View>   */}
          <View style={{ display: this.state.pl }}>
            <TrainingSchedule Up={this.Upss} dataTrainingSchedule={this.state.data.baitap} dataTrainingScheduleL={this.state.lichtap}></TrainingSchedule>
          </View>
          {/* Thông báo */}
          <View style={{ display: this.state.pl2 }}>

            <Notification></Notification>

          </View>
          {/* Dịch vụ */}
          <View style={{ display: this.state.pl3 }}>

            <Service dataService={this.state.data.dichvu} pay={(t) => { this.props.navigation.navigate('Paypal') }} />

          </View>
          {/* Tài Khoản */}
          <View style={{ display: this.state.pl4 }}>

            <Text> Muc 4</Text>

          </View>
          <Button style={{display: (this.state.isChange===true? 'flex': "none")}} success><Text> Cập Nhập </Text></Button>
          {/* end */}
        </Content>


        {/* Footer  <Button  onPress={() => { this.props.navigation.navigate('Paypal')}} success ><Text>Thanh Toán</Text></Button> */}
        <Footer style={{ backgroundColor: '#000000' }}>
          <FooterTab style={{ backgroundColor: '#000000' }}>


            <Button
              active={this.state.tab1}
              onPress={() => this.toggleTab1()}
              vertical
              badge
            >
              <Badge>
                <Text>2</Text>
              </Badge>
              <Icon active={this.state.tab1} name="grid" />
              <Text>Lịch tập</Text>
            </Button>
            <Button active={this.state.tab2} onPress={() => this.toggleTab2()}>
              <Icon active={this.state.tab2} name="alarm" />
              <Text>Thông báo</Text>
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
              <Icon active={this.state.tab3} name="cart" />
              <Text>Dịch vụ</Text>
            </Button>
            <Button active={this.state.tab4} onPress={() => this.toggleTab4()}>
              <Icon active={this.state.tab4} name="person" />
              <Text>Tài khoản</Text>
            </Button>

          </FooterTab>
        </Footer>
      </Container>
    );
  }
}

export default BadgeFooter;
