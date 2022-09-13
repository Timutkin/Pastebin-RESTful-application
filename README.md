# Test-task-third-part
Условие:

Нужно спроектировать и реализовать бэкенд (REST API) для сервиса аналогичного
pastebin.com — сервис позволяет заливать куски текста/кода ("пасту") и получать на них
короткую ссылку, которую можно отправить другим людям.
При загрузке "пасты" пользователь указывает:
1. Срок в течение которого "паста" будет доступна по ссылке (expiration time)
10 мин., 1 час, 3 часа, 1 день, 1 неделя, 1 месяц
после окончания срока получить доступ к "пасте" нельзя, в том числе и автору
2. Ограничение доступа:
public — доступна всем
unlisted — доступна только по ссылке
Для загруженной пасты выдается короткая ссылка вида
http://my-awesome-pastebin.tld/{какой-то-рандомный-хэш}, например,
http://my-awesome-pastebin.tld/ab12cd34
Пользователи могут получить информацию о последних 10 загруженных публичных "пастах".
