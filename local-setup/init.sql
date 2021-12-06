INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_MODERATOR');
INSERT INTO role(name) VALUES('ROLE_COACH');

INSERT INTO subscription_type(cost, placement_time, validity, name) VALUES (3900,'ALL_DAY',6, 'Серебряный');
INSERT INTO subscription_type(cost, placement_time, validity, name) VALUES (5900,'ALL_DAY',12, 'Золотой');
INSERT INTO subscription_type(cost, placement_time, validity, name) VALUES (4900,'EVENING',1, 'Бронзовый');

INSERT INTO training(name) VALUES ('Кардио');
INSERT INTO training(name) VALUES ('Бокс');
INSERT INTO training(name) VALUES ('Аэробика');

INSERT INTO subscription(begin_date, contract_number, end_date, subscription_type_id) VALUES ('26.11.2021', '89824655645', '27.11.2021', 1);
INSERT INTO subscription(begin_date, contract_number, end_date, subscription_type_id) VALUES ('31.12.2021', '89824561232', '01.01.2022', 2);
INSERT INTO subscription(begin_date, contract_number, end_date, subscription_type_id) VALUES ('26.11.2021', '89824655645', '27.11.2021', 3);

INSERT INTO fit_user(email, password, name, age, birthdate, gender, passport, patronymic, surname, telephone, address, img_url, balance)
VALUES('AdminProfile@gmail.com', '$2a$10$FM7DRHjUIfhmAXgsq1uKC.tmah/oymXYr1xNKCLgKMOVPCI20rJdy', 'Валерий', 50, '03.02.1971', 'M',
       '345793459', 'Александрович', 'Подкорытов', '88005553535', 'Москва, ул. Энгельса, н. 3 п.23', 'admin.jpg', 2500);
INSERT INTO fit_user(email, password, name, age, birthdate, gender, passport, patronymic, surname, telephone, address, img_url, balance)
VALUES('UserProfile@gmail.com', '$2a$10$bJK2ha21uzpVRWxDAevZDusyxZT9gsr1SeJalDFEF0wl2TpnQKQg2', 'Владимир', 43, '01.02.1978', 'M',
       '4723637495', 'Петрович', 'Столяров', '89236003343', 'Санкт-Петербург, Ленина ул., д. 3 кв.17', 'user.jpg', 0);
INSERT INTO fit_user(email, password, name, age, birthdate, gender, passport, patronymic, surname, telephone, address, img_url, balance)
VALUES ('CoachProfile@gmail.com','$2a$10$SdgV.8KAG/Jvh2Xd8x2aqOfy5XRrTlyDx4mafbH9xfb2ubN9e.AKK','Гавриил', 35, '01.06.1994', 'M',
        '2313333342', 'Иванович', 'Цуканов', '89219692138', 'г Санкт-Петербург, Большой Сампсониевский пр-кт, д 7', 'coach.jpg', 200);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (1,1);
INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (1,2);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (2,1);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (3,1);
INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (3,3);

INSERT INTO news(content, date_time, header, img_url) VALUES('Современные технологии достигли такого уровня, ' ||
                                                             'что семантический разбор внешних противодействий позволяет ' ||
                                                             'оценить значение переосмысления внешнеэкономических политик. ' ||
                                                             'Прежде всего, повышение уровня гражданского сознания ' ||
                                                             'предоставляет широкие возможности для инновационных методов ' ||
                                                             'управления процессами. Как уже неоднократно упомянуто, ' ||
                                                             'явные признаки победы институционализации объявлены ' ||
                                                             'нарушающими общечеловеческие нормы этики и морали.',
                                                             '2021-11-22', 'Современные технологии','../../assets/gym.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Кстати, базовые сценарии поведения пользователей будут смешаны ' ||
                                                             'с не уникальными данными до степени совершенной неузнаваемости, ' ||
                                                             'из-за чего возрастает их статус бесполезности. ' ||
                                                             'В целом, конечно, семантический разбор внешних противодействий ' ||
                                                             'представляет собой интересный эксперимент проверки анализа ' ||
                                                             'существующих паттернов поведения. Господа, курс на ' ||
                                                             'социально-ориентированный национальный проект говорит о ' ||
                                                             'возможностях форм воздействия.', '2021-10-02',
                                                             'Cценарии','../../assets/gym.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Значимость этих проблем настолько очевидна, что понимание ' ||
                                                             'сути ресурсосберегающих технологий создаёт предпосылки ' ||
                                                             'для глубокомысленных рассуждений. Предварительные выводы ' ||
                                                             'неутешительны: перспективное планирование однозначно фиксирует ' ||
                                                             'необходимость новых принципов формирования материально-технической ' ||
                                                             'и кадровой базы. Идейные соображения высшего порядка, а ' ||
                                                             'также выбранный нами инновационный путь влечет за собой процесс ' ||
                                                             'внедрения и модернизации как самодостаточных, так и внешне зависимых ' ||
                                                             'концептуальных решений.',
                                                             '2020-05-16', 'Значимость','../../assets/gym.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Сегодня нашему фитнес клубу исполняется год. Ровно год назад ' ||
                                                             'мы открыли наше заведение. С тех пор мы неоднакратно отличались ' ||
                                                             'наилучшим сервисом и качеством залов, что оставляло множество ' ||
                                                             'положительных эмоций у наших клиентов.',
                                                             '2021-12-01', 'День рождение','../../assets/gym.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('В выходные наш фитнес клуб работаь не будет!', '2021-11-02',
                                                             'Планы на выходные','../../assets/gym.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('20 декабря с 10:00 по 14:00 будут проводиться технические работы на ' ||
                                                             'нашем сайте.',
                                                             '2021-11-16', 'Технические работы','../../assets/gym.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Найдена денежная сумма в размере 453 тысячи рублей. Находка ' ||
                                                             'передана на пост охраны перед входом в фитнес клуб. ' ||
                                                             'Охранник делиться суммой не намерен, так что ' ||
                                                             'хозяину данной находки придется отбирать деньги силой.',
                                                             '2021-08-11', 'Найдены деньги','../../assets/gym.jpg');
