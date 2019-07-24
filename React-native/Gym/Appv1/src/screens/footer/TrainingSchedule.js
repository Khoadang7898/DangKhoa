import React, { Component } from "react";
import {
  Button,
  Text,
  Input,
  View
} from "native-base";
import { Table, Row, TableWrapper, Cell, Col, Rows } from 'react-native-table-component';
import { TouchableOpacity, Alert } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

class TrainingSchedule extends Component {
  constructor(props) {
    super(props)
    this.state = {
      isEditTranningSchedule: 'none',
      Weight: '60',
      Height: '180',
      isEditHeight: "none",
      isEditWeight: "none",
      tableHeadL: ['Ngày', 'Bài tập', 'Loại', 'Hoạt Động'],
      tableHead: ['Tên', 'Nhóm cơ', 'Mã', 'Chi tiết'],
      tableData: this.props.dataTrainingSchedule,
      tableDataL: [
        {
          t: "T2",
          b: "Tay",
          m: "t1",
          h: "no"
        },
        {
          t: "T3",
          b: "T22y",
          m: "t",
          h: "no"
        },
        {
          t: "T4",
          b: "T22y",
          m: "t",
          h: "no"
        },
        {
          t: "T2",
          b: "T22y",
          m: "t",
          h: "no"
        },
        {
          t: "T3",
          b: "T22y",
          m: "t",
          h: "no"
        },
        {
          t: "T5",
          b: "T22y",
          m: "t",
          h: "no"
        },
        {
          t: "T6",
          b: "T22y",
          m: "t",
          h: "no"
        }
      ],
      Week:['T2','T3','T4','T5','T6','T7','CN'],

      rowEdit: -1,
      colEit: -1,
      textEit: ''

    }

  }




  _alertIndex(data) {
    Alert.alert(
      'Mô tả kỹ thuật',
      data,
      [
        { text: 'OK' },
      ],
      { cancelable: false },
    );
  }
  Edit(data, type) {
    if (type == "Weight") {
      //height
      this.setState({ Weight: data })
    }
    else if (type == "Height") {
      this.setState({ Height: data })
    }
    else if (type == "TranningSchedule") {
      var edit = this.state.tableData;
      edit[this.state.rowEdit].splice(this.state.colEit, 1, data)
      this.setState({
        tableData: edit
      })
    }
  }


  render() {
    const state = this.state;
    const element = (data) => (
      <TouchableOpacity onPress={() => this._alertIndex(data)}>
        <View style={{ width: 58, height: 18, backgroundColor: '#222222', borderRadius: 2 }}>
          <Text style={{ textAlign: 'center', color: '#FFF' }}>Xem>></Text>
        </View>
      </TouchableOpacity>
    );

    return (
      <View>
        {/* Thông tin cơ bản về sức khỏe */}
        <View style={{ flexDirection: 'row', color: '#fff' }}>
          <Text style={{ color: '#fff' }}>Cân nặng :   {this.state.Weight} </Text>
          <Input onChangeText={(text) => this.Edit(text, "Weight")}
            style={{ height: 25, color: '#fff', backgroundColor: '#C0C0C0', display: this.state.isEditWeight }} />
          <Button style={{ backgroundColor: '#222222', width: 30, height: 20, textAlign: 'right' }}
            onPress={() => { this.props.Up(true), this.setState({ isEditWeight: (this.state.isEditWeight === 'none' ? 'flex' : "none") }) }} success>
            <Icon name={(this.state.isEditWeight === 'none' ? 'edit' : "check")} size={30} color="#fff" />
          </Button>
        </View>


        <View style={{ flexDirection: 'row', width: '100%' }}>
          <Text style={{ color: '#fff' }}>Chiều cao :   {this.state.Height} </Text>
          <Input onChangeText={(text) => this.Edit(text, "Height")}
            style={{ height: 25, color: '#fff', backgroundColor: '#C0C0C0', display: this.state.isEditHeight }} />
          <Button style={{ backgroundColor: '#222222', width: 30, height: 20, paddingRight: 0 }}
            onPress={() => { this.setState({ isEditHeight: (this.state.isEditHeight === 'none' ? 'flex' : "none") }) }} success>
            <Icon name={(this.state.isEditHeight === 'none' ? 'edit' : "check")} size={30} color="#fff" />
          </Button>
        </View>
        <View>

          {/* Lịch tập luyện */}
          <View style={{ marginTop: 20 }}>
            <Text style={{ color: '#fff' }}>
              Lịch tập của bạn :
            </Text>
            <Input onChangeText={(text) => this.Edit(text, "TranningSchedule")}
              style={{ height: 25, color: '#fff', backgroundColor: '#C0C0C0', display: this.state.isEditTranningSchedule }} />
            <Button style={{ backgroundColor: '#222222', width: 30, height: 20, paddingRight: 0 }}
              onPress={() => { this.setState({ isEditTranningSchedule: (this.state.isEditTranningSchedule === 'none' ? 'flex' : "none") }) }} success>
              <Icon name={(this.state.isEditTranningSchedule === 'none' ? 'edit' : "check")} size={30} color="#fff" />
            </Button>
          </View>
          <View style={{ flex: 1, padding: 16, paddingTop: 30, backgroundColor: '#222222' }}>
              <Table style={{ flexDirection: 'column' }}>
                {/* Left Wrapper */}
                {
                   this.state.Week.map((rowData, index) => (
                     <TableWrapper key={index} style={{ flexDirection: 'row' }}>
                <TableWrapper style={{ width: 80 }}>
                  <TableWrapper style={{ flexDirection: 'row' }}>
                    <Col data={[rowData]} style={{ flex: 1, backgroundColor: '#222222' }}  textStyle={{ color:'#fff', textAlign: 'center' , padding: 6}} />
                    {/* <Col data={['Title', 'Title2', 'Title3', 'Title4']} style={{ flex: 2, backgroundColor: '#f6f8fa' }} heightArr={[30, 30, 30, 30]} textStyle={{ marginRight: 6, textAlign:'right' }}></Col> */}
                  </TableWrapper>
                </TableWrapper>

                {/* Right Wrapper */}
                <TableWrapper style={{ flex: 1 }}>
                          <Rows data={ this.state.tableDataL.map((da)=>(da.t===rowData?[da.h,da.m,da.b]:null))} textStyle={{ color:'#fff', textAlign: 'center' }} />
                </TableWrapper>
                </TableWrapper>
                   ))
              }
              </Table>



            {/* <Table borderStyle={{ borderColor: 'transparent' }}>
              <Row data={state.tableHead}
                style={{ height: 40, backgroundColor: '#222222' }}
                textStyle={{ color: '#fff', margin: 6 }} />
              {
                (this.state.tableData === null ? (

                  <TableWrapper key={0} style={{ flexDirection: 'row', backgroundColor: '#222222' }}>
                    <Cell key={0}
                      data={"Không có bài tập nào"}
                      // data=    {(!(cellIndex === 3)&&!(cellIndex===0) )? element(cellData, index,cellIndex) : cellData}
                      textStyle={{ color: '#fff', margin: 6 }} />

                  </TableWrapper>
                ) : (
                    this.state.tableData.map((rowData, index) => (
                      <TableWrapper  style={{ flexDirection: 'row', backgroundColor: '#222222' }}>
                        <View style={{width: 50, height: 50, backgroundColor: 'powderblue'}} />
                        <View style={{width: 50, height: 50, backgroundColor: 'skyblue'}} />
                        <View style={{width: 50, height: 50, backgroundColor: 'steelblue'}} />
                      </TableWrapper>
                    ))))
              }
            </Table> */}
          </View>


          {/* <Table borderStyle={{ borderColor: 'transparent' }}>
              <Row data={this.state.tableHeadL}
                style={{ height: 40, backgroundColor: '#222222' }}
                textStyle={{ color: '#fff', margin: 6 }} />

              {
                (!(this.state.tableDataL === null) ?
                  (
                    <TableWrapper  style={{ backgroundColor: '#222222' }}>

                          <TableWrapper  style={{ flexDirection: 'row', backgroundColor: '#222222' }}>

                                <Col data={["T2"]} 
                                style={{ width:50,  backgroundColor: '#222222' }}
                                textStyle={{ color: '#fff', margin: 6 }} />

                                <Col
                               data= {(this.state.tableDataL.map((data,index)=>(
                               <Row key={index} data = {
                                data.t==="T2"?[(data.b),(data.m),(data.h)]:null
                              } style={{ height: 40, backgroundColor: '#222222' }}
                        textStyle={{ color: '#fff', margin: 6 }} />
                        )))}
                        />
                        
                        
                        
                        </TableWrapper>
                    </TableWrapper>
                  

                  )
                  : (<TableWrapper key={0} style={{ flexDirection: 'row', backgroundColor: '#222222' }}>
                    <Cell key={0}
                      data={"Bạn chưa có lịch tập"}
                      // data=    {(!(cellIndex === 3)&&!(cellIndex===0) )? element(cellData, index,cellIndex) : cellData}
                      textStyle={{ color: '#fff', margin: 6 }} />
                  </TableWrapper>)
                )
              }
            </Table>*/}
          <View>
            <Text style={{
              marginLeft: 60,
              marginTop: 30, color: '#fff'
            }}>
              Tên huấn lyện viên:
              </Text>
          </View>
        </View>

        {/* Các bài tập */}
        <View style={{ marginTop: 20 }}>
          <Text style={{ color: '#fff' }}>
            Các bài tập :
            </Text>
        </View>
        <View style={{ flex: 1, padding: 16, paddingTop: 30, backgroundColor: '#222222' }}>
          <Table borderStyle={{ borderColor: 'transparent' }}>
            <Row data={state.tableHead}
              style={{ height: 40, backgroundColor: '#222222' }}
              textStyle={{ color: '#fff', margin: 6 }} />
            {
              (this.state.tableData === null ? (

                <TableWrapper key={0} style={{ flexDirection: 'row', backgroundColor: '#222222' }}>
                  <Cell key={0}
                    data={"Không có bài tập nào"}
                    // data=    {(!(cellIndex === 3)&&!(cellIndex===0) )? element(cellData, index,cellIndex) : cellData}
                    textStyle={{ color: '#fff', margin: 6 }} />

                </TableWrapper>
              ) : (
                  this.state.tableData.map((rowData, index) => (
                    <TableWrapper key={index} style={{ flexDirection: 'row', backgroundColor: '#222222' }}>
                      <Cell key={0}
                        data={rowData.tenBaiTap}
                        // data=    {(!(cellIndex === 3)&&!(cellIndex===0) )? element(cellData, index,cellIndex) : cellData}
                        textStyle={{ color: '#fff', margin: 6 }} />
                      <Cell key={1}
                        data={rowData.nhomCo}
                        // data=    {(!(cellIndex === 3)&&!(cellIndex===0) )? element(cellData, index,cellIndex) : cellData}
                        textStyle={{ color: '#fff', margin: 6 }} />
                      <Cell key={2}
                        data={rowData.maBaiTap}
                        // data=    {(!(cellIndex === 3)&&!(cellIndex===0) )? element(cellData, index,cellIndex) : cellData}
                        textStyle={{ color: '#fff', margin: 6 }} />
                      <Cell key={3}
                        //data={rowData.moTaKyThuat}
                        data={element(rowData.moTaKyThuat)}
                        textStyle={{ color: '#fff', margin: 6 }} />
                    </TableWrapper>
                  ))))
            }
          </Table>
        </View>
      </View>

    );
  }
}


export default TrainingSchedule;