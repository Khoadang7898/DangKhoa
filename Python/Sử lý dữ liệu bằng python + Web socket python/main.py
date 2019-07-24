import pandas as pd
import socketio
import statistics
import math as m
import numpy as np
import Algorithms as al
from sklearn.model_selection import train_test_split
# standard Python
sio = socketio.Client()
# asyncio
sio.connect('http://localhost:8080')


@sio.on('chay')
def on_message(data):
        print('Chạy tiến trình')
        #Import cơ sở dữ liệu
        sio.emit('print-server',"Chạy tiến trình")
        df=pd.read_csv("DataSet/Dataset_Version_01.csv");
        dict_data=df.to_dict();
        # ------------- 0. PHÂN TÍCH CƠ SỞ DỮ LIỆU ---------------
        PhanTichDuLieu(dict_data)

        sio.emit('print-server'," #################### GIAI ĐOẠN 1 : TIỀN XỬ LÝ DỮ LIỆU ########################")


        sio.emit('print-server'," -------------- 1. CHỌN LỌC DỮ LIỆU ----------------------")
        sio.emit('print-server',"  LOẠI BỎ THUỘC TÍNH 'OPERATING SYSTEM' VÀ 'BROWSER'")
        dict_data=ChonLocDuLieu(dict_data,["OperatingSystems","Browser"])


        sio.emit('print-server'," -------------- 2. XỬ LÝ SAI & THIẾU DỮ LIỆU --------------")
        dict_data=XuLyDuLieuSaiThieu(dict_data)


        sio.emit('print-server'," ---------------3. XỬ LÝ TRÙNG LẶP -----------------------")
        sio.emit('print-server'," LOẠI BỎ CÁC BỘ DỮ LIỆU TRÙNG LẶP VỚI NHAU")
        dict_data=XuLyTrunglap(dict_data)

        sio.emit('print-server',"  ---------------4. XỬ LÝ MÂU THUẪN -----------------------")
        sio.emit('print-server',"  LOẠI BỎ CÁC BỘ DỮ LIỆU MÂU THUẪN VỚI NHAU")
        
        df=pd.DataFrame.from_dict(dict_data)
        dict_data_index=df.to_dict(orient="index");  #Đọc dữ liệu dưới định hướng index. --> Dữ liệu theo dòng.
        dict_data=XuLyMauThuan(dict_data_index)


        sio.emit('print-server'," ---------------5. XỬ LÝ DỮ LIỆU NHIỄU -------------------")
        dict_data=XuLyDuLieuNhieu(dict_data)

        sio.emit('print-server',"---------------6. CHIA TẬP DỮ LIỆU ----------------------")
        arr=ChiaTapDuLieu(dict_data,0.3,0.7) #Hàm trả về mảng các tập theo thứ tự sau array[data_train,data_test,target_train,target_test]
        data_train = arr[0]
        data_test = arr[1]
        full_data = np.append(data_train,data_test,axis=0)
        target_train = arr[2]
        target_test = arr[3]
        full_target = np.append(target_train,target_test,axis=0)
        list_attr = list(dict_data.keys())
        del list_attr[-1]
        attributes = list_attr
        # --------------7.1 ID3 DECISION TREE ALGORITHM---------------
        # Tạo dữ liệu đầu vào cho huấn luyện và kiểm thử
        id3 = al.DecisionTree()
        df_data_train = pd.DataFrame.from_dict(data_train)
        df_target_train = pd.DataFrame.from_dict(target_train)
        dataTrain = id3.data_target_merge(df_data_train, df_target_train, attributes, "Revenue")  # Có sẵn nhãn lớp
        id3.fit(dataTrain, attributes)

        # --------------8. KIỂM THỬ MÔ HÌNH -------------------------
        # NẾU MUỐN KIỂM THỬ BẰNG BỘ DỮ LIỆU NGẪU NHIÊN NHẬP VÀO --> CHUẨN HOÁ BỘ DỮ LIỆU ĐẦU VÀO.
        # CHUẨN HOÁ DỮ LIỆU CẦN PHÂN LỚP
        # data_request=[0,0,0,0,1,0,0.2,0.2,0,0,"Feb",1,1,1,1,"Returning_Visitor",False]
        # data_request=TienXuLy.ChuanHoaDuLieu(dict_data,data_request)
        df_data_test = pd.DataFrame.from_dict(data_test)
        df_target_test = pd.DataFrame.from_dict(target_test)
        dataTest = id3.data_test(df_data_test, attributes)  # Không có nhãn lớp
        pred = id3.predict(dataTest)

        # ---------------9. ĐÁNH GIÁ MÔ HÌNH -------------------------
        print("------------ĐÁNH GIÁ MÔ HÌNH --------------------")
        utils = al.utils()
        KFold = al.KFold()
        # Vẽ ma trận nhầm lẫn và tính toán các độ đo
        utils.print_metrics(target_test, pred,sio)
        # Tính độ đo KFold
        df_full_data= pd.DataFrame.from_dict(full_data)
        df_full_target = pd.DataFrame.from_dict(full_target)
        full_data_target=id3.data_target_merge(df_full_data, df_full_target, attributes, "Revenue")  # Tạo tập dữ liệu test có nhãn lớp
        scores=KFold.predict(full_data_target,id3,n_splits=20)
        sio.emit('print-server-kq',str(scores))
        sio.emit('hinh2',"data")
        #utils.print_scores(scores)
def removeData(dict_data,attr): #Truyền vào tên thuộc tính cần xoá
    print("*Thao tác : Xoá dữ liệu của thuộc tính \"" + attr + "\"");
    sio.emit('print-server',"Thao tác : Xoá dữ liệu của thuộc tính "+ str(attr) )
    #Tìm kiếm xem thuộc tính có tồn tại trong CSDL không
    flag=0;  # Đánh dấu thuộc tính có được tìm thấy hay không.
    for key in dict_data.keys():
        if (key==attr):
            del dict_data[attr];
            print("=> Kết quả : Xoá thành công");
            sio.emit('print-server',"=> Kết quả : Xoá thành công")
            flag=1; # Đã tìm thấy và xoá
            break;
    if (flag==0):
            print("=> Kết quả : Xoá không thành công \n(Error : Thuộc tính \""+attr+"\" không được tìm thấy.)")
            sio.emit('print-server',"=> Kết quả : Xoá không thành công ")
            sio.emit('print-server',"Error : Thuộc tính "+str(attr)+" không được tìm thấy. ")
#Hàm điều khiển việc lọc dữ liệu, truyền vào dữ liệu kiểu từ điển, và danh sách các thuộc tính cần loại bỏ
def ChonLocDuLieu(dict_data, list_attr):
    print("---------------TIẾN TRÌNH CHỌN LỌC DỮ LIỆU--------------")
    sio.emit('print-server',"---------------TIẾN TRÌNH CHỌN LỌC DỮ LIỆU--------------")
    numAttrPre=len(dict_data);
    for attr in list_attr:
        removeData(dict_data,attr);
    sio.emit('print-server',"###Kết quả TIẾN TRÌNH CHỌN LỌC DỮ LIỆU")
    sio.emit('print-server',"=>Số thuộc tính trước khi xử lý : " + str(numAttrPre))
    print("=>Số thuộc tính trước khi xử lý : ",numAttrPre)
    sio.emit("print-server","=>Số thuộc tính trước khi xử lý : "+ str(numAttrPre))
    print("=>Số thuộc tính trước sau khi xử lý : ",len(dict_data))
    sio.emit("print-server","=>Số thuộc tính trước sau khi xử lý : "+ str(len(dict_data)))
    print("--------------------------------------------------------")
    return dict_data
def XuLyDuLieuSaiThieu(data_set):
    print("------- TIẾN TRÌNH XỬ LÝ DỮ LIỆU SAI THIẾU -----------")
    sio.emit("print-server","------- TIẾN TRÌNH XỬ LÝ DỮ LIỆU SAI THIẾU -----------")
    sio.emit("print-server","Thao tác : Thay thế giá trị có tần số xuất hiện nhiều nhất cho giá trị sai & thiếu  (Trừ trường hợp sai và thiếu trên thuộc tính Phân lớp) ")
    sio.emit("print-server","Số dòng dữ liệu trước xử lý :"+ str(len(data_set["Administrative"])))
    print("*Thao tác : Thay thế giá trị có tần số xuất hiện nhiều nhất cho giá trị sai & thiếu \n" "(Trừ trường hợp sai và thiếu trên thuộc tính Phân lớp) ")
    print("Số dòng dữ liệu trước xử lý :", len(data_set["Administrative"]))
    def Kiem(k):# ham kiem tra xem k co la chuoi hay so , str la True
        list =['q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m','Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M','!','@','#','$','%','^','&','*','(',')','-','_','+','=']
        for i in list:
            if(k.count(i)!=0):
                return True
        return False
    list_sum_err= []
    err=0
    def Sai(k):
        if(list_sum_err.count(k)==0):
            list_sum_err.append(k)
    list_type=[1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0," ",12.0,13.0," "," "]#chuỗi type của từng thuộc tính theo thứ tự 1 - int " "->string
    i =0
    list_value_Month=["Jan","Feb","Mar","Apr","May","June","Jul","Aug","Sep","Oct","Nov","Dec"]
    list_value_VisitorType=["Returning_Visitor","New_Visitor"]
    list_value_Weekend=['True','False']

    list_value_Weekend2=[True,False]
    for j in list(data_set['Revenue'].keys()):# đi từng giá trị
        if(list_value_Weekend2.count(data_set['Revenue'][j])!=1):#kiểm tra key
            err=err+1
            for g in data_set.keys():#sai xóa hàng
                del  data_set[g][j]
    for k in list(data_set.keys()):#kiểm tra các thuộc tính còn lại
        if(k!="Revenue"):#Khác cột quyết định
            list_err = []#danh sách sai của cột k
            list_true = []  # list đúng -> để tìm giá trị nhiều nhất để set giá trị
            for j in list(data_set[k].keys()):#duyệt từng giá trị trong cột j là vị trí hàng
                if(Kiem(str(data_set[k][j]))==False):
                    data_set[k][j]=float(data_set[k][j])
                else:
                    data_set[k][j]=str(data_set[k][j])
                if(type(list_type[i]) != type(data_set[k][j])):#kiểm tra kiểu
                    list_err.append(j)
                    Sai(str(j))
                elif(type(list_type[i])==type(1.0)):#nếu là kiểu int
                    if(data_set[k][j]>=0):#lớn hơn 0 thì ok
                        list_true.append(j)

                    else:
                        list_err.append(j)
                        Sai(str(j))
                elif(type(list_type[i])==type(" ")):#nếu là kiểu String
                    if(k=="Month"):# nếu là cột Month
                        if(list_value_Month.count(data_set[k][j])==0 ):#kiểm tra giá trị có trong tập đc xđ trước
                            list_err.append(j)
                            Sai(str(j))
                        else:
                            list_true.append(j)
                    elif(k=="VisitorType"):#nếu là cột VisitorType
                        if (list_value_VisitorType.count(data_set[k][j]) == 0):#kiểm tra giá trị có trong tập đc xđ trước
                            list_err.append(j)
                            Sai(str(j))
                        else:
                            list_true.append(j)
                    elif(k=="Weekend"):#nếu là cột Weekend
                        if (list_value_Weekend.count(data_set[k][j]) == 0):#kiểm tra giá trị có trong tập đc xđ trước
                            list_err.append(j)
                            Sai(str(j))
                        else:
                            list_true.append(j)
            if(type(list_type[i])==type(1.0)):
                value =[]
                for b in list_true:#lấy giá trị của các hàng đúng đẫ đc lưu vị trí trong mảng list_true
                    value.append(data_set[k][b])
                f=0
                f = statistics.mean(value)
                for b in list_err:#gán các giá trị sai = giá trị trung bìnb f
                    data_set[k][b]=f
            if(type(list_type[i])==type(" ")):
                max =0;
                value =""
                if (k == "Month"):  # nếu là cột Month
                    for r in list(data_set[k].keys()):
                        if (list_value_Month.count(data_set[k][r]) > max):  # nếu số lần xuất hiện lớn nhất thì gabs max = số lần và value = giátri
                            max = list_value_Month.count(data_set[k][r])
                            value=data_set[k][r]
                elif (k == "VisitorType"):  # nếu là cột VisitorType
                    for r in list(data_set[k].keys()):
                        if (list_value_VisitorType.count(data_set[k][r]) > max):  # nếu số lần xuất hiện lớn nhất thì gabs max = số lần và value = giátri
                            max = list_value_Month.count(data_set[k][r])
                            value = data_set[k][r]
                elif (k == "Weekend"):  # nếu là cột Weekend
                    for r in list(data_set[k].keys()):
                        if (list_value_Weekend.count(data_set[k][r]) > max):  # nếu số lần xuất hiện lớn nhất thì gabs max = số lần và value = giátri
                            max = list_value_Month.count(data_set[k][r])
                            value = data_set[k][r]
                for v in list_err:
                    data_set[k][v]=value
        i=i+1

    print('Số lỗi dữ liệu sai & thiếu đã xử lý :',len(list_sum_err)+err)
    print("Số dòng dữ liệu sau xử lý : ",len(data_set["Administrative"]))
    print("=>Xử lý thành công!")
    print("----------------------------------------------------------")
    sio.emit("print-server","##### Kết quả xử lý dữ liệu sai & thiếu :")
    sio.emit("print-server",'Số lỗi dữ liệu sai & thiếu đã xử lý :'+ str(len(list_sum_err)+err))
    sio.emit("print-server","Số dòng dữ liệu sau xử lý : "+str(len(data_set["Administrative"])))
    sio.emit("print-server","=>Xử lý thành công!")
    sio.emit("print-server","----------------------------------------------------------")
    
    
    return data_set

def XuLyTrunglap(dict_data):

    sio.emit('print-server',"------------TIẾN TRÌNH XỬ LÝ TRÙNG LẶP--------------")
    print("------------TIẾN TRÌNH XỬ LÝ TRÙNG LẶP--------------")
    list_dict={}
    isFirstRun= True
    # Chuyển đổi dict_data theo cột thành dạng dữ liệu theo hàng
    for attribute in list(dict_data.keys()):
        col_data=dict_data[attribute]
        for row in list(col_data.keys()):
            if(isFirstRun):
                list_dict[row]=[]
            list_dict[row].append(col_data[row])
        isFirstRun=False
    # Dùng set để loại bỏ trùng lặp
    setData=set([])
    for row in list(list_dict.keys()):
        setData.add(tuple(list_dict[row])) #Trước khi đưa vào set phải chuyển sang tuple.
    sio.emit('print-server',"-Số dòng trước xử lý trùng lặp: ",str(len(list_dict)))
    sio.emit('print-server',"-Số dòng sau khi xử lý trùng lặp: ",str(len(setData)))
    sio.emit('print-server',"=>Số dòng trùng lặp đã xóa: ",str(len(list_dict)-len(setData)))
    sio.emit('print-server',"-------------------------------------------------------")
    print("-Số dòng trước xử lý trùng lặp: ",len(list_dict))
    print("-Số dòng sau khi xử lý trùng lặp: ",len(setData))
    print("=>Số dòng trùng lặp đã xóa: ",len(list_dict)-len(setData))
    print("-------------------------------------------------------")
    # print(set)
    #tái cấu trúc set --> dict
    dict_data_processed ={}
    isFirstRun=True
    count_row=0
    for str_row_data in setData:
        list_row_data=list(str_row_data)
        # print(list_row_data)
        count_col=0
        for attribute in dict_data.keys():
            if(isFirstRun):
                dict_data_processed[attribute]={}
            dict_data_processed[attribute][count_row]=list_row_data[count_col]
            count_col+=1
        isFirstRun=False
        count_row+=1
    return dict_data_processed

def XuLyMauThuan(dict_data):
    #Khởi tạo tham sóo
    list_tr = []
    list_true=[]
    list_fa = []
    list_fasle =[]
    set_true=set()
    #1.Chuẩn bị
           #1.1.tạo List_tr
    for row in dict_data.keys():
        # print(type(dict_data[row]))
        if (dict_data[row]["Revenue"]):
            list=[]
            for row_dict_value in dict_data[row].keys():
                list.append(dict_data[row][row_dict_value])
            list_tr.append(list)
    # print(list_tr)#test list_tr
            #1.1.1.Lọc ra thuộc tính quyết định Revenue --> tạo list_true
    for i in range(0,len(list_tr)):
        list=[]
        for j in range(0,len(list_tr[i])-1):
            list.append((list_tr[i][j]))
        list_true.append(list)
    # print(list_true)
            #1.2.tạo List_fa
    for row in dict_data.keys():
        if (dict_data[row]["Revenue"]==False):
            list=[]
            for row_dict_value in dict_data[row].keys():
                list.append(dict_data[row][row_dict_value])
            list_fa.append(list)
            #1.2.1.Lọc ra thuộc tính quyết định Revenue --> tạo list_fasle
    for i in range(0,len(list_fa)):
        list=[]
        for j in range(0,len(list_fa[i])-1):
            list.append((list_fa[i][j]))
        list_fasle.append(list)
    # print(list_fasle)
    #2.Xử lý mẫu thuẫn
            #2.1.Xóa các dòng mẫu thuẫn ở 2 list, true và fasle
    # list_true_check=[]
    # list_fasle_check=[]
    def MauThuan(list_true_check,list_fasle_check):
        for i in range(0,len(list_true_check)):
            for j in range(0,len(list_fasle_check)):
                if (list_fasle_check[j] == list_true_check[i]):#kiểm tra bên list_true có dòng giống với list_fasle
                    # if(list_true_check[-1]==1):#Nếu đã thêm chỉ số thì bỏ qua
                    #     continue
                    if (str(list_true_check[i][-1])=="1"):  #Phải xác định nó là list trước rồi mới truy xuất phần tử cuối cùng . :v
                        continue
                    else:
                        list_true_check[i].append(1)#Thêm chỉ số là 1 vào cuối để đánh dấu dòng mâu thuẫn --> Xóa sau
                    list_fasle_check[j].append(1)    #Đánh dấu mâu thuẫn chứ không xóa nữa (Giải pháp mới)
                    # del list_fasle_check[j]#Xóa dòng bên list_fasle
                    # Lỗi do dòng del trên sinh ra --> Khi xóa thì kích thước mảng thay đổi tuy nhiên thay đổi này sẽ không được
                    # cập nhật ở lệnh len(list_fasle_check) của vòng for --> Lỗi index out of range
                    # => Giải pháp : Không dùng cách đó nữa.
                    # => Đánh dấu các list mâu thuẫn ở cả 2 list true và false . Ở bước tái cấu trúc thì loại bỏ list mâu thuẫn.

    MauThuan(list_true,list_fasle)
    # print(list_fasle)#test list_fasle sau khi xử lý
    # print(list_true)#test list_fasle sau khi xử lý
    #3.Tái cấu trúc file sau khi xử lý

    for i in range(0,len(list_true)):
        list_true[i].append(True)#Thêm thuộc tính quyết để khôi phục lại như ban đầu
    for i in range(0,len(list_fasle)):
        list_fasle[i].append(False)#Thêm thuộc tính quyết để khôi phục lại như ban đầu

    list_original=[]#khởi tạo tham số list ban đầu
    list_original=list_true+list_fasle;#Gộp 2 list
    # print(list_original)#Test list ban đầu
    dict_data_processed={}#Khởi tạo tham số dict mới sau khi xử lý

            #3.1.lấy thuộc tính ban đầu
    list_attribute=[]#Tạo list thuộc tính
    for i in dict_data.keys():
        for j in dict_data[i].keys():
            list_attribute.append(j)
        break
    # print(list_attribute)#test list thuộc tính
            #3.2.khôi phục địng dạng dict_data_processed
    list_conflict=[]   #LƯU  các dòng mâu thuẫn --> Check kết quả
    count=0 # Đếm số dòng mâu thuẫn
    for i in range(0,len(list_original)):
        #Loại bỏ các dòng bị mâu thuẫn
        if(str(list_original[i][-2])=="1"):   #Phải ép sang so sánh chuỗi vì True là 1 False Là != 1
            count+=1
            list_conflict.append(list_original[i])
            continue
        dict_data_processed[i] = {}#Khởi tạo dict với key=i
        row = 0;
        for attribute in list_attribute:
            dict_data_processed[i][attribute] = list_original[i][row]#gán giá trị chào vị trị key=1(key=attribute)
            row+=1;

    ########## XUẤT KẾT QUẢ XỬ LÝ ##########
    sio.emit("print-server","### KẾT QUẢ TIẾN TRÌNH XỬ LÝ MÂU THUẪN DỮ LIỆU --------")
    sio.emit("print-server","*Số mẫu dữ liệu trước xử lý : "+ str(len(dict_data))+" (dòng)")
    sio.emit("print-server","*Số mẫu dữ liệu sau xử lý : "+str(len(dict_data_processed))+" (dòng)")
    sio.emit("print-server","=>Số mẫu dữ liệu mâu thuẫn đã xóa : "+str(count)+" (dòng)")
    sio.emit("print-server","---------------------------------------------------------")
    print("--------------TIẾN TRÌNH XỬ LÝ MÂU THUẪN DỮ LIỆU --------")
    print("*Số mẫu dữ liệu trước xử lý : ", len(dict_data)," (dòng)")
    print("*Số mẫu dữ liệu sau xử lý : ",len(dict_data_processed)," (dòng)")
    print("=>Số mẫu dữ liệu mâu thuẫn đã xóa : ",count," (dòng)");
    print("---------------------------------------------------------");
    df=pd.DataFrame.from_dict(dict_data_processed,orient="index");
    return df.to_dict()

def XuLyDuLieuNhieu(dict_data):
    sio.emit('print-server', "-------------TIẾN TRÌNH XỬ LÝ DỮ LIỆU NHIỄU----------------")
    sio.emit('print-server', "*CÁC THUỘC TÍNH GÂY NHIỄU CẦN XỬ LÝ :")
    print("-------------TIẾN TRÌNH XỬ LÝ DỮ LIỆU NHIỄU----------------")
    print("*CÁC THUỘC TÍNH GÂY NHIỄU CẦN XỬ LÝ :");
    ############ GĐ1 : TỪ KẾT QUẢ PHÂN TÍCH SUY RA CÁC THUỘC TÍNH NHIỄU ########
    ## ProductRelated : Có 311 loại , nhiễu nhẹ -> KHông khử

    ## Administrative_Duration, Informational_Duration, ProductRelated_Duration :
    # -->Giá trị liên tục sẽ gây nhiễu cây quyết định
    # --> Dùng kỹ thuật xử lý làm rời rạc hóa dữ liệu.

    #Đối với ProductRelated_Duration
    # + Min : 0 ms - Max : 63973.52223 ms
    # --> Chọn khoảng [0-65000] (ms)
    # ĐỘ rộng giỏ 500. (Vì dữ liệu phân bố không đều và tập trung ở vùng thấp)

    #Administrative_Duration
    #Min :  0.0 |  Max : 3398.75
    # Khoảng [0-3500]
    #-> Độ rộng giỏ :100

    #Informational_Duration
    #Min :  0.0 |  Max : 2549.375
    #->Giỏ [0;2600]
    #-> ĐỘ rộng giỏ : 100

    ## BounceRates, ExitRates
    #Min :  0.0 (1%) |  Max : 0.2 (20%)
    #Độ chênh lệch rất nhỏ, Chỉ làm tròn số
    #Độ rộng giỏ : 0.01 (1%)

    ## PageValues : Giá trị liên tục nên sẽ gây nhiễu cây quyết định
    # --> Dùng kỹ thuật Bining và Smoothing
    # + Min : 0 - Max : 361,7637419
    # --> Chọn khoảng [0-400] - Độ rộng giỏ : 50
    # ==> Gán nhãn : Là string của khoảng đó

    #######################################################################################################################
    # ---- GIAI ĐOẠN 2 : XỬ LÝ CÁC THUỘC TÍNH GÂY NHIỄU
    # Group1=["Administrative","Informational", "ProductRelated"]   # Nhớm nhiễu 1
    Group1=["Administrative_Duration", "Informational_Duration"]
    Group2=["ProductRelated_Duration"]
    Group3=["BounceRates", "ExitRates"]
    Group4=["PageValues"]
    sio.emit('print-server',"Nhóm 1 : "+str(Group1))
    sio.emit('print-server',"Nhóm 2 : "+str(Group2))
    sio.emit('print-server',"Nhóm 3 : "+str(Group3))
    sio.emit('print-server',"Nhóm 4 : "+str(Group4))

    print("Nhóm 1 : ",Group1)
    print("Nhóm 2 : ",Group2)
    print("Nhóm 3 : ",Group3)
    print("Nhóm 4 : ",Group4)
    #ĐỊNH NGHĨA HÀM XỬ LÝ THEO PHƯƠNG PHÁP BINNING VÀ SMOOTHING (KHÔNG LÀM TRƠN BẰNG GIÁ TRI TRUNG BÌNH CỦA GIỎ MÀ GÁN NHÃN CHO GIỎ)
    #Định nghĩa hàm chia giỏ --> Trả về nhãn của giỏ (String)
    def getBasketLabel1(width_basket,value,min_value): #Truyền vào độ rộng giỏ và giá trị cần phân giỏ, giá trị min của giỏ
        if(value==min_value):  # Trường hợp đặt biệt (Vì không con giỏ nào bên dưới nên cho nó vào giỏ [min_value;min_value+width_baske]
            right_value=min_value+width_basket;
        else:
            right_value=m.ceil(value/width_basket)*width_basket #Giá trị bên phải của giỏ
        left_value=right_value-width_basket #Giá trị bên trái của giỏ
        #Xác định khoảng --> Chuyển sang String --> Trả về
        label="" #Nhãn của giỏ:
        if(value<=min_value+width_basket): #Nếu giá trị nằm trong giỏ đầu tiên thì cận bên trái của khoảng là "["
            label="["+str(left_value)+"-"
        else:
            label="("+str(left_value)+"-"
        label+=str(right_value)+"]"

        return label

    def countValue(dict_data, attr): #Thống kế các giá trị kèm theo là số lần xuât hiện của giá trị đó trên 1 thuộc tính
        dict_values ={}
        for col in  dict_data[attr].keys():
            value=dict_data[attr][col]
            if value in dict_values.keys():
                dict_values[value]=dict_values[value]+1
            else :
                dict_values[value]=1
        return dict_values
    #Hàm trả về giá trị trung bình của giỏ --> Làm trơn Smoothing
    #Hàm gán giỏ 1 - Phương pháp bining và smoothing
    def pointLableForBasket1(list_attribute, data_set, width_basket, min_value):
        for Attribute in list_attribute: #Duyệt lần lượt các thuộc tính cần xử lý
            col_data = data_set[Attribute]  # Lấy lần lượt cột dữ liệu của các thuộc tính nhiễu
            dict_period={} #Khởi tạo biến lưu các giỏ và value là tổng giá trị của giỏ --> Mục đích để làm trơn
            count_value=len(col_data) #Tính số lượng các giá trị
            #GĐ1 : Chia giỏ
            for row in col_data.keys(): # Duyệt lần lượt giá trị từng hàng của cột dữ liệu
                label=getBasketLabel1(width_basket,col_data[row],min_value)
                if(label in dict_period.keys()):
                    dict_period[label]+=col_data[row];
                else:
                    dict_period[label]=col_data[row];
                col_data[row]=label
            # GĐ2 : Làm trơn
            dict_values=countValue(data_set,Attribute)
            for row in col_data.keys():
                label=col_data[row]
                # col_data[row]=round(dict_period[label]/dict_values[label],2)
                if (Attribute=="BounceRates"or Attribute=="ExitRates"):
                    #Nếu là giá trị % thì nhân 100 --> Số quá nhỏ.
                    col_data[row]=(dict_period[label]/dict_values[label])*100
                else:
                    col_data[row]=round(dict_period[label]/dict_values[label],2)

    #Hàm gán giỏ 2 - Phương pháp rời rạc hóa dữ liệu
    # min_width_basket : Khoảng cách của giỏ nhó nhất trong các giỏ, list_label : danh sách các nhãn sẽ gán cho giỏ,
    # các giá trị trong list_label được sắp xếp tương ứng với thứ tự các giỏ chia theo khoảng cách nhỏ nhất.
    # VD : Trường hợp xử lý Group2 thì các giỏ sẽ được chia lại và gán như sau :
    # Để áp dụng giải thuật sẽ thực hiện trong hàm pointLabelForBasket2 thì Các thuộc tính trong Group 2 được chia lại
    # Khoảng cách giỏ : 5000 ms - Số giỏ : 13 giỏ - min : 0 ms
    # [0-5000] : Very Short
    # (5000-10000] :Short
    # (10000-15000] : Short
    # ... Tương tự
    # ĐỊNH NGHĨA HÀM CHO PHƯƠNG PHÁP RỜI RẠC HÓA DỮ LIỆU
    # def getBasketLabel2(min_width_basket,value,min_value,list_label): #Truyền vào độ rộng giỏ và giá trị cần phân giỏ, giá trị min của giỏ
    #     if (value==min_value): # TRường hợp đặc biệt. Vì không còn giỏ nào thấp hơn nên gán cho value vào giỏ [min_value- min_value+min_width_basket]
    #         num_basket=1 # Số thứ tự giỏ mà value thuộc vào
    #     else :
    #         num_basket=m.ceil(value/min_width_basket)
    #     label=list_label[num_basket-1] #Tham chiếu để lấy nhãn của giỏ , -1 là vì trong list thì chỉ số giỏ 1 là 0.
    #     return label
    # def pointLabelForBasket2(list_attribute, data_set,min_width_basket,min_value,list_label):
    #     for Attribute in list_attribute: #Duyệt lần lượt các thuộc tính cần xử lý
    #         col_data = data_set[Attribute]  # Lấy lần lượt cột dữ liệu của các thuộc tính nhiễu
    #         for row in col_data.keys(): # Duyệt lần lượt giá trị từng hàng của cột dữ liệu
    #             label=getBasketLabel2(min_width_basket,col_data[row],min_value,list_label)
    #             col_data[row] = label

    #Xử lý nhiễu - Theo phương pháp Bining và Smoothing
    print("-Thao tác : Xử lý nhóm 1")
    sio.emit('print-server',"-Thao tác : Xử lý nhóm 1")
    pointLableForBasket1(Group1,dict_data,100,0)
    # print(data_set["PageValues"])
    print("=>Xử lý thành công")
    sio.emit('print-server',"=>Xử lý thành công")
    sio.emit('print-server',"-Thao tác : Xử lý nhóm 2")

    print("-Thao tác : Xử lý nhóm 2")
    pointLableForBasket1(Group2,dict_data,500,0)
    # print(data_set["PageValues"])
    print("=>Xử lý thành công")
    sio.emit('print-server',"=>Xử lý thành công")

    sio.emit('print-server',"-Thao tác : Xử lý nhóm 3")
    print("-Thao tác : Xử lý nhóm 3")
    pointLableForBasket1(Group3,dict_data,0.01,0.0)
    print("=>Xử lý thành công")

    sio.emit('print-server',"=>Xử lý thành công")
    sio.emit('print-server',"-Thao tác : Xử lý nhóm 4")
    print("-Thao tác : Xử lý nhóm 4")
    pointLableForBasket1(Group4,dict_data, 50, 0)
    print("=>Xử lý thành công")
    sio.emit('print-server',"=>Xử lý thành công")
    sio.emit('print-server',"----------------------------------------------------------")
    print("-----------------------------------------------------------")
    sio.emit('print-server',"---- GIAI ĐOẠN 3 : XỬ LÝ TRÙNG LẶP SAU KHI XỬ LÝ NHIỄU -----------------------------")
    XuLyTrunglap(dict_data);
    return dict_data

def ChiaTapDuLieu(dict_data,test_size,train_size):
    sio.emit('print-server',"-----------TIẾN TRÌNH PHÂN CHIA TẬP DỮ LIỆU------------")
    sio.emit('print-server',f'*Thao tác : Chia tập dữ liệu theo tỉ lệ {train_size*100}% : {test_size*100}%')
    #Thay đổi định dạng tập dữ liệu
    df=pd.DataFrame.from_dict(dict_data)
    dict_data=df.to_dict(orient="split")
    list_data_target=dict_data['data']
    list_data=[]
    list_target=[]
    for data_target in list_data_target :
        list_target.append(data_target[-1])
        del data_target[-1]
        list_data.append(data_target)
    arr_data=np.asarray(list_data)
    arr_target=np.asarray(list_target)
    data_train,data_test,target_train,target_test=train_test_split(arr_data,arr_target,test_size=test_size,train_size=train_size);
    sio.emit('print-server',"-Tổng số dòng dữ liệu :"+str(len(dict_data['data']))+" (dòng)")
    sio.emit('print-server',"-Tập huấn luyện có :"+str(len(data_train))+" (dòng)")
    sio.emit('print-server',"-Tập kiểm thử có :"+str(len(data_test))+" (dòng)")
    sio.emit('print-server',"=>Phân chia thành công !")
    
    print("-------------------------------------------------------")
    return np.asarray([data_train,data_test,target_train,target_test])

def PhanTichDuLieu(dict_data):
    # Thống kê các giá trị min và max của các thuộc tính tính để xác định các thuộc tính có khả năng gây nhiễu
    def Min_Max(dict):
        print("Kiểu dữ liệu : ", type(dict[0]))
        Min=Max=dict[0];
        for index in dict.keys():
            if(Min>dict[index]):
                Min=dict[index]
            if(Max<dict[index]):
                Max=dict[index]
        return [Min,Max]
    #Hàm tính giá trị trung bình của 1 thuộc tính
    def Average(dict):
        if type(dict[0]) is int or type(dict[0]) is float:
            sum=0;
            for index in dict.keys():
                sum+=int(dict[index])
            return sum/len(dict)
        return None;
    #Hàm đếm số lượng giá trị của 1 thuộc tính
    def countValue(dict_data, attr): #Thống kế các giá trị kèm theo là số lần xuât hiện của giá trị đó trên 1 thuộc tính
        dict_values ={}
        for col in  dict_data[attr].keys():
            value=dict_data[attr][col]
            if value in dict_values.keys():
                dict_values[value]=dict_values[value]+1
            else :
                dict_values[value]=1
        return dict_values
    #Hàm tím giá trị phổ biến
    def searchPopularValue(dict_values):
        popularValue=list(dict_values.keys())[0]
        for value in dict_values:
            if dict_values[popularValue]<dict_values[value]:
                popularValue=value
        return [popularValue,dict_values[popularValue]]

    sio.emit('print-server-ts',"--------------TIẾN TRÌNH PHÂN TÍCH DỮ LIỆU---------------")
    print("--------------TIẾN TRÌNH PHÂN TÍCH DỮ LIỆU---------------")
    for attr in dict_data.keys():
        sio.emit('print-server-ts',"-------------" + str(attr) + "----------------")
        dict_values=countValue(dict_data,attr);
        listValue= Min_Max(dict_data[attr]);
        listMaxAppearValue=searchPopularValue(dict_values)
        sio.emit('print-server-ts','print-server-ts',"Number of Values :"+str((dict_values)))
        sio.emit('print-server-ts',"Min : "+str(listValue[0])+"|  Max :"+str(listValue[1]))
        sio.emit('print-server-ts',"Average: "+str(Average(dict_data[attr])))
        sio.emit('print-server-ts',"Popular Value : "+str(listMaxAppearValue[0])+"|  Frequency : "+str(listMaxAppearValue[1]))
    sio.emit("----------------------------------------------------------")

def PhanTichDuLieuClass(arrTarget):
    count=0
    for value in arrTarget:
        if (value==True):
            count +=1
    print("True Class :",count)
    print("False Class :",len(arrTarget)-1)


