# holiday-manager-api

有給管理アプリAPI

## dbマイグレーション

flywayによるDBマイグレーションを行います。
src/main/resources/db/migration 配下にSQLを配置することでアプリ起動時にSQLが適用されます。
命名規則は  V{バージョン番号}__{SQLの説明}.sql です。

### dbマイグレーション前準備

アプリを起動する前に manager DBを作成する必要があります。
[手順]
src/main/resources/db/sql/create-database-manager.sql
上記SQLを適用する