
# Java 문법 과제 : 계산기
### 스파르타 부트캠프 Spring 3기 권지원입니다.

### Step1
class 를 따로 생성하지 않고 App class 안에 모든 속성, 기능이 들어가있습니다.
while문 안에 기호 -> 첫번째 정수 -> 두번째 정수 순서로 엔터로 구분지어 입력하면 결과값이 출력됩니다.
종료조건: 기호 대신 exit 입력 

### Step2
class가 나뉘어 정의됩니다.
App         main 함수, Scanner가 존재합니다. 
            Calculator 클래스의 c.start() (기능 선택부)를 반복문 내에서 호출합니다.
            종료조건: 기능선택 시 exit 입력 
Calculator  - start     종료, 계산결과 조회, 계산 시작을 선택할 수 있습니다. 
                        계산결과 조회-> getResult, ifRemove 메서드 호출 
                        ifRemove 메서드는 id를 매개변수로 rmResult 메서드 호출
                        계산 시작 -> ioHandler 호출 
            - ioHandler input을 받아 calculate 메서드에 매개변수로 전달합니다. 
                        내부에서 inputNum, chkDivideZeroError, inputSign사용
                        각각은 입력에 대한 예외처리를 위해 함수로 분리되어 있습니다.
            - calculate 숫자와 기호를 받아 계산 후 setResult 메서드 (저장) 호출
CalResult   HashMap 이 결과를 저장하는 용도로 선언되어있고, 데이터관리에 관한 메서드 정의
            - setResult id 값을 key로 하여 결과값을 string 형태로 저장 
            - getResult hashMap 출력
            - rmResult  id를 입력받아 key-value set을 삭제 

### Step3
class가 나뉘어 정의됩니다. Step2와 유사하고 추가되거나 변경된 기능만 기재합니다.
App         main 함수, Calculator 가 generic 형태로 선언됩니다.
ArithmeticCalculator 
            상수형태의 Enum을 사용하여 연산을 정의합니다.
            - calculate 실제 연산부
            - getSign   String 형태의 sign을 OperatorType 형태로 반환
            - start     값을 입력받아 결과값중 입력값보다 큰 value를 반환하는 find 기능 추가
                        findNum() 함수를 호출 -> compareNum 함수에 매개변수 double 전달
            - ioHandler op 값을 저장하고 연산을 switch 문이 아닌 op.calculate 호출하여 사용
            - inputNum  string 으로 받음 
CalResult   hashMap 의 value 로 Point class의 객체를 사용
            - compareNum    stream을 사용해 더 큰 결과값을 출력(printMethod 사용)
Point       hashMap의 value로 쓰임 연산식 String 과 결과값 double이 캡슐화 되어있습니다.
            
