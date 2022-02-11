use dbstok;

#Tablodan verileri getirme
SELECT * FROM computers;

#Tabloya veri ekleme (İstenen ürün sayısı kadar ürün eklenebilir)
INSERT INTO computers (item_no, brand, processor, ram, ssd, price, num_of_stock) VALUES (10, 'Apple', 'Intel i3', 4, 256, 7100, 18);

#Tablodan item_no değerine ait herhangi bir sütunun değerini güncelleme
UPDATE computers SET num_of_stock = 10 WHERE item_no = 18; 

# Filtreleme (brand, processor, ram, ssd sütunlarına göre)
SELECT * FROM computers where null is null;
SELECT * FROM computers where (coalesce('Apple') is null or brand in ('Apple')) 
		AND (coalesce('Intel i5') is null or processor in ('Intel i5'))
        AND (coalesce('256') is not null and ssd in ('256'));