# CV-Project-by-Dang-Khoa
Gồm các phần: 
  + Java : -Quản lý phòng gym với Java (Server  + giao Diện quản lý + App khách hàng)
            + Thanh toán Paypal
            + Quản lý : 
            + Khách hàng ( Các thông tin cơ bản + lịch tập + chuẩn đoán dinh dưỡng) 
            + Nhân viên (Thông tin cơ bản + lương + đi làm + thời gian ra vào + Đánh giá)
            + Cơ sở hạ tầng ( Thông tin cơ bản + tình trạng (tốt xấu ) + thông báo khi hết hạn )
            + Tài chính ( Doanh thu + Chi tiêu + lợi nhậu + so sánh theo nhiệm kỳ năm tháng tuần ..)
          - App - > React Native (android + ios )
             + Tích hợp thanh toán trên App :  Dùng Paypal để thanh toán.
             + Quản lý thông tin Account
             + Tạo lịch , xem giám sát lịch tập.
             + Đăng ký chỉnh sửa dịch vụ
             + Phản hồi ý kiến 
          - Server: Spring boot 
          - Giao diện : JavaFX
          - Database: MySQL (Dùng JDBC để giao tiếp) (Database free Heroku)
          
  + Python : - Xử lý dữ liệu : Thuật toán : + ID3( Cây quyết định -> Dựa trên độ tin cậy của thông tin để phân cây ) 
                                            + Navi bayes : (  Dựa vào tần số xuất hiện ứng với mỗi giá trị của                                                                             thuộc tính)
                                          + Website : Python thuần(Không dùng Django)
                                          +Dữ liệu đầu vào dạng bảng (Chưa được chọn lọc) -> Xử lý dữ liệu (Sai, thiếu, Nhiễu,                                           trùng lặp, phân bổ lại dữ liệu theo túi,..) -> Xanh dựng model (Hai thuật toán ID3                                              và Navi bayes) -> tranning -> test (Độ chính sác 80->86%)
                                          
                                          
  + Nodejs : Websocket -> Tạo phòng chat + chat giữa 2 nhiều người (Dùng socket.io + Express.js)
             Website thông tin du lịch : + Facebook API -> Đăng Nhập 
                                         + Mongodb (database Azure free ) 
                                         + Cho phép người dùng tạo bài đăng, chỉnh sửa .
                                         + Theo dõi tác giả ưu thích link share (Facebook)
                                         + Xắp xếp bài đăng theo tương tác xem thích ...
                                         + 
