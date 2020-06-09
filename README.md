# bisikletkiralama
İnternet Programcılığı Dönem Projesi
Grup Üyeleri					Github commit isimleri   	Github Kullanıcı Adları

1-)Muhammed Talha YILMAZ  : 02170201062  	Talha Yılmaz 			mty-c
2-)Selahattin YILDIRIM : 02180201061		DazlakGandalf			SelahattinYildirim1
3-)Ahmet İkbal KAYMAZ : 02180201048		Misafir				ahmetikbalkaymaz
4-)Hüseyin ALTIKULAÇ : 02180001501		HuseyinAltikulac		HuseyinAltikulac

Projenin En Verimli Haliyle Çalışabilmesi İçin ;
1-)Proje Chrome tarayıcısında çalıştırılmalı.
2-)İnternete bağlı bir pc de çalıştırılmalı.(Bazı resimler hizliresim bağlantısı olarak eklendiği için)
3-)DocumentController içindeki uploadTo dosya yolu projedeki upload klasörüne göre çalıştırılacak pc'de değiştirilmeli.
4-)Util içindeki DBConnection bağlantısı çalıştırılacak pc'ye göre düzenlenmeli.
5-)Admin işlemlerinin gerçekleştirilmesi için;
kullanıcı adı : admin 
Şifre : 12345
6-)Dosya işlemleri admin panel üzerindedir.
7-)Kullanıcı panelinde menüdeki seçeneklere tıkladığınız zaman ürünleri görebilmek için scroll u aşağı indirmeniz gerekmektedir.
8-)AdminPanel üzerindeki documents e tıkladığınız zaman scroll u aşağı indirmeniz gerekmektedir.
9-)Eğer eklediğiniz ürünün kullanıcısını DEFAULT olarak seçerseniz kullanıcı yok olarak ürünü ekler.


One to Many İlişkisi : Projedeki onetomany ilişkisi bir ürünün bir kullanıcısı olabilir ama bir kullanıcı birden fazla ürüne sahip olabilir şeklinde yapıldı.

Many To Many İlişkisi : Projedeki manytomany ilişkisi bir ürünün birden fazla kategorisi olabilir aynı şekilde bir kategoride de birden fazla ürün olabilir şeklinde yapıldı.


Kullanıcı Yetkisine Göre Sitenin Çalışması ; Admin olarak giriş yapıldığında admin panelin gözükmesi , normal kullanıcı giriş yaptığında ise admin panelin gözükmemesi.

1-)Admin Kullanıcı : Admin Panel üzerinde sitedeki kullanıcıların ve ürünlerin eklenmesi , silinmesi , güncellenmesi ve tekil kaydın okunması gerçekleştirilebilmektedir.
(Tekil kayıt okuma Admin panel üzerindeki kullanıcılar ve documents kısmında vardır.)

2-)Normal Kullanıcı : Normal kullanıcı giriş yaptığında farklı kategorilerdeki ürünleri gözlemleyip kiralama özelliğine ve kiraladıktan sonra iade etme yetkisine sahiptir.
Farklı kullanıcılar tarafından kiralanmış ürünler de sitede listelenmektedir.
