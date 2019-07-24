from collections import Counter
from sklearn.model_selection import train_test_split
import pandas as pd
import math
from matplotlib import pyplot as plt
from sklearn import metrics
import numpy as np

class utils:
    def print_scores(self, scores,sio):
        print(np.asarray(scores))
        df = pd.Series(scores)
        plt.bar(range(len(df)), df.values, align='center')
        plt.xticks(range(len(df)), df.index.values, size='small')
        plt.show()
        sio.emit('hinh2')
        ###

    #Hàm xuất thông tin về các độ đo : độ chính xác, độ phủ, Precision, f1 Score
    def print_metrics(self, predictions, Y_test,sio):
        #Tính toán các độ đo dựa vào function có sẵn trong module metrics của thư viện sklearn
        accuracy = metrics.accuracy_score(predictions,Y_test)
        precision = metrics.precision_score(predictions,Y_test,pos_label=1)
        recall = metrics.recall_score(predictions,Y_test,pos_label=1)
        f1_score = metrics.f1_score(predictions,Y_test,pos_label=1)
        sio.emit('print-server-kq',"Accuracy : " +str(accuracy))
        sio.emit('print-server-kq',"Precision : " +str(precision))
        sio.emit('print-server-kq',"recall : " +str(recall))
        sio.emit('print-server-kq',"f1 Score : " +str(f1_score))
        print("Accuracy : " +str(accuracy))
        print("Precision : " +str(precision))
        print("recall : " +str(recall))
        print("f1 Score : " +str(f1_score))

        confusion_matrix = metrics.confusion_matrix(Y_test,predictions,labels=[1,0]);
        print("--------------MA TRẬN NHẦM LẪN----------------")
        print(confusion_matrix);
        sio.emit('hinh1',"data")
        #.drawConfusionMatrix(confusion_matrix,sio);        

    def drawConfusionMatrix(self, confusion_matrix):
        labels = ['True', 'False']
        fig = plt.figure()

        ax = fig.add_subplot(111)
        cax = ax.matshow(confusion_matrix)
        fig.colorbar(cax)

        plt.title('Confusion matrix')
        ax.set_xticklabels([''] + labels)
        ax.set_yticklabels([''] + labels)
        plt.xlabel('Predicted')
        plt.ylabel('True')
        plt.show()
        ##################

class DecisionTree:
    #Hàm tạo cây ID3
    def fit(self, trainData, attributes):
        print("+++++++++++++TIẾN TRÌNH XÂY DỰNG CÂY QUYẾT ĐỊNH+++++++++++++")
        global target , trainedTree
        self.target = 'Revenue';  #Tên thuộc tính phân lớp
        tree = self.id3(df = trainData, target_attribute_name = self.target, attribute_names = attributes)
        self.trainedTree = tree #Cây đã được xây dựng
        print("#### => MÔ HÌNH CÂY QUYẾT ĐỊNH ID3 ĐÃ XÂY DỰNG THÀNH CÔNG ! ###")
        print("---------------------------------------------------------------")
        return tree

    #Hàm nối tập dữ liệu và tập kết quả phân lớp tương ưng
    def data_target_merge(self, X ,Y,atributes,target):
        source = X.copy()
        source.columns=atributes
        newY = Y.copy()
        source[target] = newY # Tiến hành ghép 2 tập data và target lại
        return source
    #Hàm xây dựng tập dữ liệu kiểm thử
    def data_test(self,X,attributes):
        source = X.copy()
        source.columns = attributes
        return source
    def id3(self, df, target_attribute_name, attribute_names, default_class = False):
    
        #Thống kê tần số các giá trị của thuộc tính phân lớp
        cnt = Counter(x for x in df[target_attribute_name])
        keys = list(cnt.keys())  # Lấy các loại giá trị
        ### KIỂM TRA CÁC ĐIỀU KIỆN DỪNG CỦA VIỆC XÂY DỰNG CÂY ###
        ## First check: Tập dữ liệu có đồng nhất hay không?
        # Nếu có trả về nhãn đồng nhất
        if len(cnt) == 1:
            return keys[0]
        
        ## Second check: Kiểm tra tập dữ liệu có rỗng hay không hoặc danh sách thuộc tính có rỗng không?
        # Nếu có trả về nhãn mặt định (Ở đây là =1)
        elif df.empty or (not attribute_names):
            return default_class 
        
        ## Otherwise: Tiến hành chia tập dữ liệu !
        else:
            print
            # Get Default Value for next recursive call of this function:
            # Lấy giá trị mặt định cho lần đệ quy kế tiếp của hàm này. Giá trị này là nhãn xuất hiện nhiều nhất.
            index_of_max = list(cnt.values()).index(max(cnt.values()))  # Lấy số lượng dòng dữ liệu lớn nhất của nhãn
            default_class = index_of_max # Giá trị phổ biến nhất của thuộc tính phân lớp trong dataset
            
            # Chọn thuộc tính tốt nhất để rẽ nhánh cây quyết định (Thuộc tính có độ lợi thông tin lớn nhất)
            # print("---------------TÍNH ĐỘ LỢI THÔNG TIN THÀNH PHẦN------------------")
            gainz = [self.information_gain(df, attr, target_attribute_name) for attr in attribute_names] # Tính độ lợi thông tin
            gainz = list(gainz)
            index_of_max = gainz.index(max(gainz))  #Lấy ra chỉ số có độ lợi thông tin lớn nhất trong list độ lợi thông tin
            best_attr = attribute_names[index_of_max] #Lấy ra tên thuộc tính có độ lợi thông tin cao nhất
            # print("# =>THUỘC TÍNH ĐƯỢC CHỌN :",best_attr)
            # print("------------------------------------------------------------------")
            # Tạo một cây rỗng nút gốc là thuộc tính tốt nhất
            tree = {best_attr:{}}
            # Cập nhật lại danh sách các thuộc tính điều kiện --> Tiếp tục xây cây
            remaining_attribute_names = [i for i in attribute_names if i != best_attr]
            
            # Chia tập dữ liệu ra theo các giá trị của thuộc tính được chọn phân nhánh
            # Ở mỗi tập dữ liệu con , gọi đệ quy thuật toán này.
            # Thêm vào cây vừa khởi tạo nhánh cây con
            # Nhánh cây con này là kết quả của việc thực hiện đệ quy hàm này.
            for attr_val, data_subset in df.groupby(best_attr): # Groupby tập dữ liệu theo thuộc tính được chọn
                subtree = self.id3(data_subset, target_attribute_name, remaining_attribute_names)
                tree[best_attr][attr_val] = subtree   #Thêm nút cây con vào cây
            return tree


    def entropy(self, probs):
        '''
        Nhận vào 1 list lần lượt chứa xác suất xảy ra các giá trị của thuộc tính phân lớp.
        --> Tính độ bất định của tình trạng này.
        '''
        return sum( [-prob*math.log(prob, 2) for prob in probs] )
        
    def entropy_of_list(self, a_list):
        '''
        Nhận vào một danh sách các giá trị rời rạc của 1 thuộc tính
        --> Trả về độ bật định các giá trị của thuộc tính này.
        '''        
        # Đếm số lượng các giá trị của thuộc tính
        cnt = Counter(x for x in a_list)
        
        # Xác định tỉ lệ của mỗi giá trị trong thuộc tính.
        num_instances = len(a_list)*1.0
        probs = [x / num_instances for x in cnt.values()]
        
        # Tính độ bất định - Truyền vào các tỉ lệ
        return self.entropy(probs)

    def information_gain(self, df, split_attribute_name, target_attribute_name, trace=0):
        '''
        Nhận vào một DataFrame của các thuộc tính, và định lượng độ bất định của thuộc tính phân lớp
        (target_attribute) sau khi thực hiện tập dữ liệu dựa trên thuộc tính điều kiện (split_attribute)
        '''
        
        # Phân chia dữ liệu theo giá trị của thuộc tính điều kiện.
        df_split = df.groupby(split_attribute_name) #Hàm này sẽ trả về 1 dataframe với dữ liệu được gom lại.
        
        # Tính toán Entrophy cho thuộc tính phân lớp , as well as Proportion of Obs in Each Data-Split
        nobs = len(df.index) * 1.0
        #Hàm .agg sẽ áp dụng lần lượt 2 function entropy_of_list va lambda trên tập df_split
        #--> Tính Entropy của từng tập dữ liệu con và tỉ lệ của tập dữ liệu con đó trên tập gốc
        arr_data=[]  # Lưu kết quả tính Entropy trên mỗi nhánh giá trị của thuộc tính
        for attr, datasub in df_split :
            arr=list(dict(datasub.agg({target_attribute_name : [self.entropy_of_list, lambda x: len(x)/nobs] })[target_attribute_name]).values())
            arr.insert(0,attr)
            arr_data.append(arr)
        #Tao Frame
        df_agg_ent = pd.DataFrame(data=arr_data,columns=[split_attribute_name,"Entropy","PropObservations"])
        if trace: # Giúp để hiểu được Cái fxn đang thực hiện:
            print(df_agg_ent)
        
        # Tính độ lợi thông tin
        new_entropy = sum( df_agg_ent['Entropy'] * df_agg_ent['PropObservations'] )
        old_entropy = self.entropy_of_list(df[target_attribute_name])
        return old_entropy-new_entropy

    def classify(self, instance, tree, default):
        attribute = list(tree.keys())[0]  #Lấy ra nút gốc
        if instance[attribute] in list(tree[attribute].keys()):
            result = tree[attribute][instance[attribute]]
            if isinstance(result, dict): # Nếu kết quả trả về là một từ điển thì đây là 1 nhánh của cây --> Đệ quy
                return self.classify(instance, result, default)
            elif result is not None: #Nếu nó không None thì là nhãn phân lớp
                return result # this is a label
            else:
                return default #Không có dữ liệu phân lớp, hầu như sẽ không rơi vào trường hợp này, trường hợp này nhằm tránh lỗi.
        else:
            return default #Không có cơ sở để phân lớp, vì giá trị kiểm thử lần đầu xuất hiện.
        
    def predict(self, testData):
        #Áp dụng mô hình đề phân lớp lần lượt cho các bộ trong testData
        return testData.apply(lambda obj: self.classify(instance = obj, tree = self.trainedTree, default=False) , axis=1)

#KỸ THUẬT ĐÁNH GIÁ HIỆU SUẤT CỦA MỘT MÔ HÌNH
class KFold:
    def predict(self, data, model, n_splits= 10, shuffle = True):
        print("----------- ĐÁNH GIÁ BẰNG KỸ THUẬT KFOLD -----------")
        print(f"=>ĐÁNH GIÁ ĐỘ CHÍNH XÁC NGẪU NHIÊN {n_splits} LẦN")
        scores = []
        for i in range(0 , n_splits):
            sample = data.sample(frac= 0.3, replace = shuffle)
            predictions = model.predict(sample)
            precision = metrics.precision_score(sample['Revenue'],predictions,pos_label=1)
            scores.append(precision)
        return scores
