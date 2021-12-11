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

INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (3, 'г Санкт-Петербург, Большой Сампсониевский пр-кт, д 7', '35', 200, '1994-06-01 00:00:00.000000', 'CoachProfile@gmail.com', 'M', 'coach.jpg', true, 'Гавриил', '2313333342', '$2a$10$SdgV.8KAG/Jvh2Xd8x2aqOfy5XRrTlyDx4mafbH9xfb2ubN9e.AKK', 'Иванович', 'Цуканов', '89219692138');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (1, 'Москва, ул. Энгельса, н. 3 п.23', '50', 64567064, '1971-02-03 00:00:00.000000', 'AdminProfile@gmail.com', 'M', 'admin.jpg', true, 'Валерий', '345793459', '$2a$10$FM7DRHjUIfhmAXgsq1uKC.tmah/oymXYr1xNKCLgKMOVPCI20rJdy', 'Александрович', 'Подкорытов', '88005553535');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (2, 'Санкт-Петербург, Ленина ул., д. 3 кв.17', '43', 0, '1978-02-01 00:00:00.000000', 'UserProfile@gmail.com', 'M', 'user.jpg', true, 'Владимир', '4723637495', '$2a$10$bJK2ha21uzpVRWxDAevZDusyxZT9gsr1SeJalDFEF0wl2TpnQKQg2', 'Петрович', 'Столяров', '89236003343');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (6, 'Омская обл, рп Русская Поляна, ул Жмыха', '35', 0, '1991-03-02 03:00:00.000000', 'AlbertIvanov@gmail.com', 'M', 'turtle.jpg', true, 'Альберт', '345346346346436', '$2a$10$jvQaKxxNSzPtMlw3dvkkTOsJQ.HiK1kqiP7BpjW7e6JD3FY6a3Uxa', 'Петрович', 'Иванов', '8890383253456');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (7, 'г Москва, Семеновская наб', '23', 0, '1999-01-01 03:00:00.000000', 'SemenDmitr@yandex.ru', 'F', null, true, 'Семён', '363467347', '$2a$10$H3Xl/TaR7LsN8m.59OwcqeusMRgMNQYJHjif/gO6stTFJVShbIeJ6', 'Дмитриевич', 'Дмитриев', '893842385234');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (8, 'г Санкт-Петербург, Московский пр-кт', '29', 0, '1988-01-01 03:00:00.000000', 'SemenSwalowIvanovich@gmail.com', 'M', null, true, 'Семён', '73548543', '$2a$10$R0p/YbupxVm7/ApNgQxxtOblJovpK/05s/X2Ox.08K/SgGkKbZwjO', 'Иванович', 'Свалов', '88005553535');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (9, 'г Нижний Новгород, пер Вайгач', '22', 0, '1993-12-08 03:00:00.000000', 'alexSemen@gmail.com', 'M', null, true, 'Александр', '1234111333', '$2a$10$BwvoV8qA5eOPB6W161MSher39a.c6VgloDXxFxXGmxqfw.13h6I0a', 'Семёнович', 'Семёнов', '88005553535');
INSERT INTO fit_user (id, address, age, balance, birthdate, email, gender, img_url, is_enabled, name, passport, password, patronymic, surname, telephone) VALUES (12, 'г Великий Новгород, ул Большая Санкт-Петербургская', '20', 0, '2005-11-16 03:00:00.000000', 'IAmVagon@gmail.com', 'M', null, true, 'Артём', '345634645', '$2a$10$7cNdd94fPOP9KenDb4XeFOo9f6pQIj2zf845CkEjTB2jVlEZU4m8i', 'Максимович', 'Вагонов', '88008720101');

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (1,1);
INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (1,2);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (2,1);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (3,1);
INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (3,3);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (4,1);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (5,1);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (6,1);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (7,1);

INSERT INTO fit_user_roles(fit_user_id, role_id) VALUES (8,1);

INSERT INTO news(content, date_time, header, img_url) VALUES('Современные технологии достигли такого уровня, ' ||
                                                             'что семантический разбор внешних противодействий позволяет ' ||
                                                             'оценить значение переосмысления внешнеэкономических политик. ' ||
                                                             'Прежде всего, повышение уровня гражданского сознания ' ||
                                                             'предоставляет широкие возможности для инновационных методов ' ||
                                                             'управления процессами. Как уже неоднократно упомянуто, ' ||
                                                             'явные признаки победы институционализации объявлены ' ||
                                                             'нарушающими общечеловеческие нормы этики и морали.',
                                                             '2021-11-22', 'Современные технологии', null);
INSERT INTO news(content, date_time, header, img_url) VALUES('Кстати, базовые сценарии поведения пользователей будут смешаны ' ||
                                                             'с не уникальными данными до степени совершенной неузнаваемости, ' ||
                                                             'из-за чего возрастает их статус бесполезности. ' ||
                                                             'В целом, конечно, семантический разбор внешних противодействий ' ||
                                                             'представляет собой интересный эксперимент проверки анализа ' ||
                                                             'существующих паттернов поведения. Господа, курс на ' ||
                                                             'социально-ориентированный национальный проект говорит о ' ||
                                                             'возможностях форм воздействия.', '2021-10-02',
                                                             'Сценарий поведения','fitness-club.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Значимость этих проблем настолько очевидна, что понимание ' ||
                                                             'сути ресурсосберегающих технологий создаёт предпосылки ' ||
                                                             'для глубокомысленных рассуждений. Предварительные выводы ' ||
                                                             'неутешительны: перспективное планирование однозначно фиксирует ' ||
                                                             'необходимость новых принципов формирования материально-технической ' ||
                                                             'и кадровой базы. Идейные соображения высшего порядка, а ' ||
                                                             'также выбранный нами инновационный путь влечет за собой процесс ' ||
                                                             'внедрения и модернизации как самодостаточных, так и внешне зависимых ' ||
                                                             'концептуальных решений.',
                                                             '2020-05-16', 'Значимость','group-training.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Сегодня нашему фитнес клубу исполняется год. Ровно год назад ' ||
                                                             'мы открыли наше заведение. С тех пор мы неоднакратно отличались ' ||
                                                             'наилучшим сервисом и качеством залов, что оставляло множество ' ||
                                                             'положительных эмоций у наших клиентов.',
                                                             '2021-12-03', 'День рождения','swimming-pool.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('В выходные наш фитнес клуб работать не будет!', '2021-11-02',
                                                             'Планы на выходные','running.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('20 декабря с 10:00 по 14:00 будут проводиться технические работы на ' ||
                                                             'нашем сайте.',
                                                             '2021-11-12', 'Технические работы',null);
INSERT INTO news(content, date_time, header, img_url) VALUES('Найдена денежная сумма в размере 453 тысячи рублей. Находка ' ||
                                                             'передана на пост охраны перед входом в фитнес клуб. ' ||
                                                             'Охранник делиться суммой не намерен, так что ' ||
                                                             'хозяину данной находки придется отбирать деньги силой.',
                                                             '2021-08-14', 'Найдены деньги','fitness-equipment.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('В один декабрьский день саванну засыпал пушистый снежок. ' ||
                                                             'Реки покрылись хрупким льдом. Обындевелые лианы стали похожи ' ||
                                                             'на завитушки белого серпантина. ' ||
                                                             'В воздухе повисла молочно - голубая изморозь.' ||
                                                             'Учёные слоны утеплились шерстяными шарфами и занялись ' ||
                                                             'исследованием загадочного явления. Носороги взялись за лопаты. ' ||
                                                             'Мартышки лепили снеговиков. Попугаи съезжали на сноубордах и ' ||
                                                             'катались на санках, как в настоящем бобслее. Тигры и львы играли ' ||
                                                             'в хоккей. Лишь крокодил остался ни при чём.',
                                                             '2020-05-18', 'Зимний праздник в саванне','swim-pool.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Противоположная точка зрения подразумевает, что интерактивные ' ||
                                                             'прототипы призывают нас к новым свершениям, которые, в свою очередь, ' ||
                                                             'должны быть представлены в исключительно положительном свете. ' ||
                                                             'Лишь стремящиеся вытеснить традиционное производство, нанотехнологии ' ||
                                                             'набирают популярность среди определенных слоев населения, а значит, ' ||
                                                             'должны быть в равной степени предоставлены сами себе. ' ||
                                                             'Каждый из нас понимает очевидную вещь: курс на социально-ориентированный ' ||
                                                             'национальный проект предполагает независимые ' ||
                                                             'способы реализации форм воздействия.',
                                                             '2021-12-07', 'Точка зрения','fitness-club-building.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('С учётом сложившейся международной обстановки, ' ||
                                                             'постоянное информационно-пропагандистское обеспечение ' ||
                                                             'нашей деятельности создаёт необходимость включения ' ||
                                                             'в производственный план целого ряда внеочередных мероприятий ' ||
                                                             'с учётом комплекса глубокомысленных рассуждений. ' ||
                                                             'Задача организации, в особенности же новая модель ' ||
                                                             'организационной деятельности требует определения ' ||
                                                             'и уточнения первоочередных требований.', '2021-11-02',
                                                             'Производственный план','personal-training.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Лишь базовые сценарии поведения пользователей ' ||
                                                             'формируют глобальную экономическую сеть ' ||
                                                             'и при этом - объявлены нарушающими общечеловеческие нормы этики и морали.',
                                                             '2021-11-19', 'Этика',null);
INSERT INTO news(content, date_time, header, img_url) VALUES('Ясность нашей позиции очевидна: экономическая повестка ' ||
                                                             'сегодняшнего дня создаёт необходимость включения ' ||
                                                             'в производственный план целого ряда внеочередных ' ||
                                                             'мероприятий с учётом комплекса направлений ' ||
                                                             'прогрессивного развития. Равным образом, повышение ' ||
                                                             'уровня гражданского сознания предопределяет высокую ' ||
                                                             'востребованность благоприятных перспектив!',
                                                             '2021-08-21', 'Гражданское сознание','exercise-equipment.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Предварительные выводы неутешительны: дальнейшее развитие ' ||
                                                             'различных форм деятельности играет определяющее значение ' ||
                                                             'для переосмысления внешнеэкономических политик. ' ||
                                                             'Мы вынуждены отталкиваться от того, что убеждённость ' ||
                                                             'некоторых оппонентов требует от нас анализа системы обучения ' ||
                                                             'кадров, соответствующей насущным потребностям. Кстати, ' ||
                                                             'некоторые особенности внутренней политики могут быть ' ||
                                                             'представлены в исключительно положительном свете.',
                                                             '2020-05-26', 'Наши оппоненты','fitness-club.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('С учётом сложившейся международной обстановки, ' ||
                                                             'социально-экономическое развитие предполагает ' ||
                                                             'независимые способы реализации приоритизации разума ' ||
                                                             'над эмоциями. Принимая во внимание показатели успешности, ' ||
                                                             'граница обучения кадров способствует повышению качества ' ||
                                                             'системы обучения кадров, соответствующей насущным потребностям.',
                                                             '2021-12-02', 'Качество','swimming-pool.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Значимость этих проблем настолько очевидна, что экономическая повестка ' ||
                                                             'сегодняшнего дня в значительной степени обусловливает ' ||
                                                             'важность соответствующих условий активизации.', '2021-11-02',
                                                             'Значимость проблем','running.jpg');
INSERT INTO news(content, date_time, header, img_url) VALUES('Разнообразный и богатый опыт говорит нам, ' ||
                                                             'что понимание сути ресурсосберегающих технологий ' ||
                                                             'является качественно новой ступенью ' ||
                                                             'соответствующих условий активизации.',
                                                             '2021-11-12', 'Богатый опыт','personal-training');
INSERT INTO news(content, date_time, header, img_url) VALUES('Современные технологии достигли такого уровня, ' ||
                                                             'что повышение уровня гражданского сознания ' ||
                                                             'прекрасно подходит для реализации направлений ' ||
                                                             'прогрессивного развития. В целом, конечно, ' ||
                                                             'высокотехнологичная концепция общественного уклада ' ||
                                                             'однозначно фиксирует необходимость форм воздействия.',
                                                             '2021-08-10', 'Уровень гражданского сознания',null);