## Консольное приложение:
На вход передается целое число, больше 0, кратное 2 (n). Создается файл с именем out.txt, в который пишется значение 0. Создается два потока, работающих параллельно, каждый поток в цикле, считывает значение из файла out.txt увеличивает его на 1, выводит в консоли старое значение, новое значение и идентификатор потока (идентификатор может быть произвольным), и записывает обратно в файл.
В конечном итоге оба потока должны успешно завершить свою работу, и в консоль должно вывестись содержимое файла out.txt (которое должно быть равно заданному на входе n).