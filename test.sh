#!/bin/sh

for file_java in test/programs/student-test-cases/execute/*/*java; do
    test_name=${file_java%.*}
    file_name=`basename $file_java`
    file_exec=${file_name%.*}
    echo $file_exec
    make TEST_FILE=$file_java run_test
    jasmin *j
    java $file_exec > exec_out
    diff $test_name.out exec_out
    if [ $? -ne 0 ]
    then
        echo "Error in test $test_name"
        echo "Press enter to continue or ^C to abort (and leave temporary files)"
        read discard
    fi
    # Following is based on some dangerous assumptions, should be safe thx to git :)
    rm *j
    rm *class
    rm exec_out
done
