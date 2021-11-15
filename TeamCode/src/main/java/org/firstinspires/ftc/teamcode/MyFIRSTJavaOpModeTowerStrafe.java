package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Linear opmode with tower and arm (no claw) Strafe", group = "Linear Opmode")
public class MyFIRSTJavaOpModeTowerStrafe extends LinearOpMode {
    // Declare OpMode members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor towerSpinner;
    private double dt;
    private double lastTime = 0;


    public DcMotor leftArm;

    //public Servo    leftClaw;
    //public Servo    rightClaw;

    // private Gyroscope imu;
    // private DcMotor motorTest;
    // private DigitalChannel digitalTouch;
    // private DistanceSensor sensorColorRange;
    // private Servo servoTest;
    @Override
    public void runOpMode() {
        // imu = hardwareMap.get(Gyroscope.class, "imu");
        // motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        // digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        // sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        // servoTest = hardwareMap.get(Servo.class, "servoTest");
        // telemetry.addData("Status", "Initialized");
        // telemetry.update();
// Wait for the game to start (driver presses PLAY) waitForStart();
// run until the end of the match (driver presses STOP) while (opModeIsActive()) {
        telemetry.addData("Status", "Running");
        telemetry.update();
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        towerSpinner = hardwareMap.get(DcMotor.class, "towerSpinner");

         leftArm = hardwareMap.get(DcMotor.class, "leftArm");
/*
         leftClaw  = hardwareMap.get(Servo.class, "leftHand");
         rightClaw = hardwareMap.get(Servo.class, "rightHand");
        // leftClaw.setPosition(MID_SERVO);
        // rightClaw.setPosition(MID_SERVO);
        leftClaw.setDirection(Servo.Direction.FORWARD); //FIX
        rightClaw.setDirection(Servo.Direction.REVERSE);//FIX
        //leftClaw.setPosition(leftClaw.getPosition() + 0.42);
        //rightClaw.setPosition(rightClaw.getPosition() + 0.42);
        // Wait for the game to start (driver presses PLAY)*/
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            dt = runtime.time() - lastTime;
            // Setup a variable for each drive wheel to save power level for telemetry
            double drive = -gamepad1.left_stick_y/2;
            double strafe = gamepad1.left_stick_x/2;
            double turn = gamepad1.right_stick_x/2;

            double frontLeftPower = Range.clip(drive - strafe + turn, -1.0, 1.0);
            double frontRightPower = Range.clip(drive + strafe - turn, -1.0, 1.0);
            double backLeftPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
            double backRightPower = Range.clip(drive - strafe - turn, -1.0, 1.0);

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

           // telemetry.addData("Claw position: ", leftClaw.getPosition());
            //Arm

             //down Arm
             if (gamepad1.right_trigger != 0) {
             leftArm.setDirection(DcMotor.Direction.REVERSE); //FIX
             leftArm.setPower(0.5); //FIX
             }
             else if(gamepad1.right_trigger == 0){
             leftArm.setPower(0.0);
             }

            //up Arm
             if (gamepad1.left_trigger != 0) {
             leftArm.setDirection(DcMotor.Direction.FORWARD); //FIX
             leftArm.setPower(0.5); //FIX
             }
             else if(gamepad1.left_trigger == 0) {
             leftArm.setPower(0.0);
             }

                /*
                //open claw
             if (gamepad1.right_bumper)//FIX
             {
             leftClaw.setDirection(Servo.Direction.REVERSE)  ; //FIX
             rightClaw.setDirection(Servo.Direction.FORWARD);  //FIX
             leftClaw.setPosition(leftClaw.getPosition() + 0.05*dt); //set to closed position
             rightClaw.setPosition(rightClaw.getPosition() + 0.05*dt); //set to closed position
             }

              //close claw
             if (gamepad1.left_bumper)
             {
                 leftClaw.setDirection(Servo.Direction.FORWARD)  ; //FIX
                 rightClaw.setDirection(Servo.Direction.REVERSE);  //FIX
                 leftClaw.setPosition(leftClaw.getPosition() - 0.05*dt); //set to closed position
                 rightClaw.setPosition(rightClaw.getPosition() - 0.05*dt); //set to closed position

             }

            */


            //TowerSpinner

            if(gamepad1.triangle) {
                towerSpinner.setDirection(DcMotor.Direction.FORWARD);
                towerSpinner.setPower(1);
            }
            else
            {
                towerSpinner.setPower(0.0);
            }

            if(gamepad1.cross) {
                towerSpinner.setDirection(DcMotor.Direction.REVERSE);
                towerSpinner.setPower(1);
            }
            else
            {
                towerSpinner.setPower(0.0);
            }




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
            lastTime = runtime.time();
        }

    }

}

// Need code for moving arm side to side
// Need code for close and opening claw (left and right button)