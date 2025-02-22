# no.jansenh/siri

NeTEx SIRI application.

## Installation

Download from https://github.com/no.jansenh/siri-ett

## Usage

Run the project directly, via `:exec-fn`:

    $ clojure -X:run-x

Run the project, overriding the name to be greeted:

    $ clojure -X:run-x :arg '"value"'

Run the project directly, via `:main-opts` (`-m no.jansenh.siri-et`):

    $ clojure -M:run-m

Run the project, overriding the name to be greeted:

    $ clojure -M:run-m value


Run the project's tests:

    $ clojure -T:build test

Run the project's CI pipeline and build an uberjar:

    $ clojure -T:build ci

Run that uberjar:

    $ java -jar target/no.jansenh/siri-et-0.1.0-SNAPSHOT.jar

## Options

## Examples

## License

Copyright © 2025 Henning Jansen

GNU General Public License v3.0
