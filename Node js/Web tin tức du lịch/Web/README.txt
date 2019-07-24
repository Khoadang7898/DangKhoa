## SEO_Project
#Develop and Seo a traveling website
-----------------------------------------------------------------------------------------------------
****TRONG MỖI THƯ MỤC ĐỀU CÓ FILE HƯỚNG DẪN. NHỚ ĐỌC. SAU ĐÂY SẼ LÀ MỘT SỐ LƯU Ý KHI LÀM VIỆC CHUNG.
-----------------------------------------------------------------------------------------------------
*VỀ KHU VỰC LÀM VIỆC CỦA THÀNH VIÊN :
- Việc phát triển website sẽ được chia thành các nhánh, mỗi thành viên sẽ được cấp quyền vào một hoặc nhiều nhánh (phụ thuộc vào nội dung đảm nhận).
- Mỗi thành viên lưu ý : Chỉ thao tác trên nhánh của mình. Không được tự ý thay đổi nội dung nhánh khác.
------------------------------------------------------------------------------------------------------
*VỀ COMMIT:
- Mỗi commit phải được chú thích rõ ràng những thay đổi, nguyên nhân, mục đích thay đổi --> Thuận lợi cho việc review, chia sẽ code.
	+Cấu trúc một commit message :
		<Phần tiêu đề commit> : Mô tả thay đổi một cách ngắn ngọn, dễ hiểu.
				-Dòng trống-
	<Phần giải thích > : Nêu ra nguyên nhân, mục đích, kết quả thay đổi.
- Không nên có quá nhiều và quá ít commit
	+ Các commit chỉ nên được tao ra tại những thời điểm có thay đổi quan trọng.
	+ Không nên giải quyết nhiều vấn đề trong cùng 1 thời điểm và chỉ tạo chỉ 1 commit --> Khó review, Khó hiểu, Khó kiểm soát, Khó phục hồi
	+ Trường hợp có nhiều vấn đề thì nên giải quyết từng vấn đề, mỗi vấn đề sau khi giải quyết xong thì tạo 1 bản commit để lưu trữ.

---TRÊN ĐÂY CHỈ LÀ NHỮNG LƯU Ý CƠ BẢN NHẤT. NẾU CÁC BẠN CÓ CÁCH LÀM TỐT HƠN CÓ THỂ ÁP DỤNG---


--------------------------------------------------------------------------------------------------------
*****HƯỚNG DẪN DATABASE*******
- HƯỚNG DẪN :
  Tạo Kết nối db bằng cú pháp  :
      var mongoClient = require("mongodb").MongoClient;
      mongoClient.connect(config.db.uri, function (err, client) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        (do something)
      });
  Trong đó config.db.uri là link đến db đã cài đặt sẳn trong module config/config.js , các bạn không được thay đổi bất kì biến nào
