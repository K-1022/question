-- storeテーブル

INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(1, '和食', '和風ダイニング あかり', 'akari.jpg', '新鮮な魚介を使用した和食を楽しめるお店。', 3000, '160-0022', '東京都新宿区1-1-1', '03-1234-5678', '無休', '11:00', '22:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(2, 'イタリアン', 'イタリアンキッチン トラットリア', 'italian-luce.png', '本場のパスタとピザを楽しめるカジュアルなレストラン。', 3500, '150-0002', '東京都渋谷区2-2-2', '03-2345-6789', '月曜日', '12:00', '23:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(3, '中華', '中華料理 祥龍', 'chinese-daifuku.png', '本格中華をリーズナブルに楽しめる。', 2000, '110-0005', '東京都台東区3-3-3', '03-3456-7890', '火曜日', '11:00', '22:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(4, 'カフェ', 'ベーカリーカフェ 風の丘', 'cafe-paris.png', '自家製パンとコーヒーが楽しめる落ち着いたカフェ。', 1500, '164-0001', '東京都中野区4-4-4', '03-4567-8901', '水曜日', '8:00', '19:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(5, '焼肉', '焼肉 てんてん', 'yakiniku-nikusho.png', '厳選された肉を炭火焼で楽しむお店。', 5000, '141-0021', '東京都品川区6-6-6', '03-6789-0123', '無休', '17:00', '23:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(6, 'フレンチ', 'フレンチレストラン ボンヌ', 'french-blanc.png', '本格フレンチをお手頃価格で提供するレストラン。', 4000, '100-0005', '東京都千代田区5-5-5', '03-5678-9012', '日曜日', '11:30', '21:30');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(7, 'ベトナム料理', 'ベトナム料理 フォーの家', 'vietnam-hanoi.png', '本格フォーと春巻きを楽しめるベトナム料理店。', 1800, '173-0004', '東京都板橋区15-15-15', '03-8901-2345', '月曜日', '11:00', '21:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(8, '洋食', '洋食屋 ステーキハウス', 'western-yama.png', 'ジューシーなステーキと豊富なサイドメニューが自慢。', 4500, '141-0031', '東京都品川区7-7-7', '03-9012-3456', '無休', '11:00', '22:30');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(9, 'パン屋', 'パン屋 むぎの実', 'bakery-coco.png', '手作りパンが豊富に揃うアットホームなベーカリー。', 800, '174-0051', '東京都板橋区8-8-8', '03-0123-4567', '火曜日', '7:00', '18:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(10, 'カレー', 'カレー専門店 スパイス', 'curry-bangbang.png', 'スパイシーで本格的なカレーが楽しめる店。', 1200, '170-0005', '東京都豊島区8-8-8', '03-8901-2345', '無休', '10:00', '21:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(11, '居酒屋', '居酒屋 ほろ酔い', 'horoyoi.jpg', 'おいしい料理とお酒が楽しめる居酒屋。', 3000, '171-0021', '東京都豊島区20-20-20', '03-0123-4567', '無休', '17:00', '24:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(12, 'デザート', 'スイーツ工房 夢の森', 'yumemori.jpg', '種類豊富なスイーツが楽しめるお店。', 1500, '176-0001', '東京都練馬区17-17-17', '03-7890-1234', '水曜日', '10:00', '19:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(13, 'テイクアウト', 'テイクアウト専門店 グルメ', 'takeout.jpg', 'お手軽に美味しい料理をテイクアウト。', 1200, '113-0033', '東京都文京区14-14-14', '03-6789-0123', '無休', '10:00', '20:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(14, 'ピザ', 'ピザハウス クラシック', 'classic.jpg', '本格ナポリピザが楽しめるお店。', 2000, '112-0005', '東京都文京区13-13-13', '03-3456-7890', '火曜日', '11:00', '23:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(15, 'バー', 'バー リュクス', 'bar_luxe.jpg', '洗練された雰囲気のバーで特別なひとときを。', 4000, '160-0023', '東京都新宿区21-21-21', '03-2222-3333', '月曜日', '18:00', '3:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(16, '寿司', '寿司 みなと', 'sushi-uo.png', '新鮮なネタを使用した本格寿司店。', 5000, '105-0001', '東京都港区3-3-3', '03-3456-7890', '水曜日', '11:00', '22:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(17, 'お好み焼き', 'お好み焼き ひろし', 'okonomiyaki_hiroshi.jpg', 'ボリューム満点のお好み焼きが自慢のお店。', 1200, '130-0005', '東京都墨田区9-9-9', '03-7890-1234', '火曜日', '17:00', '22:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(18, 'バイキング', 'バイキングレストラン 彩り', 'viking_irodori.jpg', '豊富なメニューが楽しめるバイキングレストラン。', 3000, '152-0004', '東京都目黒区10-10-10', '03-4567-8901', '無休', '11:30', '21:30');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(19, 'バー', 'カクテルバー アトモス', 'cocktail_atmos.jpg', 'お洒落なカクテルが楽しめるバー。', 3500, '150-0031', '東京都渋谷区2-2-2', '03-2345-6789', '日曜日', '18:00', '2:00');
INSERT IGNORE INTO stores (id, genre, name, image_name, description, price, postal_code, address, phone_number, holiday, open_time, close_time) VALUES(20, 'デザート', 'デザートカフェ スイート', 'sweet_cafe.jpg', '豊富なデザートとコーヒーが楽しめるカフェ。', 2000, '153-0004', '東京都目黒区11-11-11', '03-6789-0123', '水曜日', '10:00', '19:00');


-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

-- userテーブル
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (1, '有料', '侍 太郎', 'サムライ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '同意します。',  1, true);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (2, '無料', '侍 花子', 'サムライ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '同意します。', 2, true);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (3, '有料', '侍 義勝', 'サムライ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (4, '有料', '侍 幸美', 'サムライ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (5, '有料', '侍 雅', 'サムライ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (6, '有料', '侍 正保', 'サムライ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (7, '無料', '侍 真由美', 'サムライ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (8, '無料', '侍 安民', 'サムライ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (9, '無料', '侍 章緒', 'サムライ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (10, '無料', '侍 祐子', 'サムライ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (11, '無料', '侍 秋美', 'サムライ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.samurai@example.com', 'password', '同意します。', 1, false);
INSERT IGNORE INTO users (id, course, name, furigana, postal_code, address, phone_number, email, password, checks, role_id, enabled) VALUES (12, '無料', '侍 信平', 'サムライ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.samurai@example.com', 'password', 1, '同意します。', false);

-- genreテーブル
INSERT IGNORE INTO genre (id, genre) VALUES (1, '和食');
INSERT IGNORE INTO genre (id, genre) VALUES (2, '洋食');
INSERT IGNORE INTO genre (id, genre) VALUES (3, 'イタリアン');
INSERT IGNORE INTO genre (id, genre) VALUES (4, 'フレンチ');
INSERT IGNORE INTO genre (id, genre) VALUES (5, 'ベトナム料理');
INSERT IGNORE INTO genre (id, genre) VALUES (6, '中華');
INSERT IGNORE INTO genre (id, genre) VALUES (7, '焼肉');
INSERT IGNORE INTO genre (id, genre) VALUES (8, '居酒屋');
INSERT IGNORE INTO genre (id, genre) VALUES (9, 'ピザ');
INSERT IGNORE INTO genre (id, genre) VALUES (10, '寿司');
INSERT IGNORE INTO genre (id, genre) VALUES (11, 'カレー');
INSERT IGNORE INTO genre (id, genre) VALUES (12, 'お好み焼き');
INSERT IGNORE INTO genre (id, genre) VALUES (13, 'バーガー');
INSERT IGNORE INTO genre (id, genre) VALUES (14, 'パン屋');
INSERT IGNORE INTO genre (id, genre) VALUES (15, 'ベーカリー');
INSERT IGNORE INTO genre (id, genre) VALUES (16, 'バイキング');
INSERT IGNORE INTO genre (id, genre) VALUES (17, 'バー');
INSERT IGNORE INTO genre (id, genre) VALUES (18, 'カフェ');
INSERT IGNORE INTO genre (id, genre) VALUES (19, 'デザート');
INSERT IGNORE INTO genre (id, genre) VALUES (20, 'テイクアウト');


-- reviewsテーブル
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (1, 1, 1, '侍 太郎', '★★★★☆', 'ネタの鮮度が抜群！特にマグロが最高でした。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (2, 1, 2, '侍 花子', '★★★★☆', '新鮮なネタが自慢の本格寿司。口の中でとろける食感に感動しました！');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (3, 1, 3, '侍 義勝', '★★★★★', '何度訪れても新しい味に出会えるので、リピート必至です。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (4, 1, 4, '侍 幸美',  '★★★★★', '特に旬のネタが楽しめるのが嬉しい。毎回新しい発見があります。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (5, 1, 5, '侍 雅',  '★★★★☆', '銀座でこのクオリティが5000円から楽しめるのはお得です！');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (6, 1, 6, '侍 正保', '★★★★★', '料理の盛り付けが美しく、目でも楽しめました！味も申し分なし。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (7, 1, 7, '侍 真由美', '★★★★★', 'スタッフの対応が親切で、居心地の良い時間を過ごせました。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (8, 1, 8, '侍 安民', '★★★★☆', '食材の新鮮さが際立っていて、旬の味を楽しめました。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (9, 1, 9, '侍 章緒',  '★★★★★', 'お酒の種類が豊富で、料理とのペアリングが楽しめました。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (10, 1, 10, '侍 祐子','★★★★★', '日本酒のラインナップも豊富で、料理との相性が抜群でした。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (11, 1, 11, '侍 秋美', '★★★★★', '握りたての寿司を味わえる贅沢な時間が忘れられません。');
INSERT IGNORE INTO review (id, store_id, user_id, name, star, comments) VALUES (12, 1, 12, '侍 信平', '★★★★☆', 'お土産用の寿司もあり、家でも楽しめるのが嬉しいポイント！');





