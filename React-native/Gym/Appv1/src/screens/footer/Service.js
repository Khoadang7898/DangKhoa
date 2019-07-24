import React, { Component } from 'react';
import { Button, Card, CardItem, Text, Body,Form, Item, Picker ,Icon, View } from 'native-base';
export default class Service extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isChane: 'none',
          selected2: "Vip",
          oldKind: "Vip",
          isPay: false,
          pay:20
        };
      }
      onValueChange2(value) {
        this.setState({
          pay: value
        });
      }
  render() {
    var pit=5;
    return (
      <View>
      <View>
         <Card>
           <CardItem header>
             <Text>Thông tin gói dịch vụ của bạn</Text>
           </CardItem>
           <CardItem>
             <Body>
             <Text>
                {this.state.selected2}
            </Text>
             </Body>
           </CardItem>
           <CardItem footer>
           <Button onPress={() => {this.setState({isChane : (this.state.isChane==='none'? 'flex': "none") , oldKind : this.state.selected2,isPay:true})}} success><Text> {(this.state.isChane==='none'? 'Đổi gói': "Xác nhận")} </Text></Button>
           <Button style={{display : this.state.isChane}} onPress={() => {this.setState({isChane : (this.state.isChane==='none'? 'flex': "none"), selected2 : this.state.oldKind})}} danger ><Text>Hủy</Text></Button>
           </CardItem>
        </Card>
        </View>

        <View style = {{display : this.state.isChane}}>

        <Form>
           <Item picker>
             <Picker
               mode="dropdown"
               iosIcon={<Icon name="arrow-down" />}
               style={{ width: undefined ,color:'#fff'}}
    
               placeholderStyle={{ color: "#bfc6ea" }}
               placeholderIconColor="#007aff"
               selectedValue={pit}
               onValueChange={this.onValueChange2.bind(this)}
             >
               {
                 this.props.dataService.map((data,index)=>(<Picker.Item key={index} label={data.maGoi} value={data.gia} />))
               
               }
             </Picker>
           </Item>
         </Form>
        </View>
        <View>
          
        </View>
        <View>
        <Button style={{display : (this.state.isPay ? 'flex':'none')}} onPress={this.props.pay} success ><Text>Thanh Toán</Text></Button>
        
        </View>
        </View>
    );
  }
}