package org.usfirst.frc.team3735.robot.pipelines;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import edu.wpi.first.wpilibj.vision.VisionPipeline;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.vision.ContoursOutputPipeline;

/**
* PegPipelineTest class.
*
* <p>An OpenCV pipeline generated by GRIP.
*
* @author GRIP
*/
public class PegPipelineLSNTest5 implements VisionPipeline, ContoursOutputPipeline {

	//Outputs
	private Mat resizeImageOutput = new Mat();
	private Mat cvDilateOutput = new Mat();
	private Mat cvErode0Output = new Mat();
	private Mat cvBitwiseAnd0Output = new Mat();
	private Mat hsvThresholdOutput = new Mat();
	private Mat hslThresholdOutput = new Mat();
	private Mat cvBitwiseAnd1Output = new Mat();
	private Mat cvErode1Output = new Mat();
	private Mat blurOutput = new Mat();
	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * This is the primary method that runs the entire pipeline and updates the outputs.
	 */
	@Override	public void process(Mat source0) {
		// Step Resize_Image0:
		Mat resizeImageInput = source0;
		double resizeImageWidth = 320.0;
		double resizeImageHeight = 240.0;
		int resizeImageInterpolation = Imgproc.INTER_CUBIC;
		resizeImage(resizeImageInput, resizeImageWidth, resizeImageHeight, resizeImageInterpolation, resizeImageOutput);

		// Step CV_dilate0:
		Mat cvDilateSrc = resizeImageOutput;
		Mat cvDilateKernel = new Mat();
		Point cvDilateAnchor = new Point(-1, -1);
		double cvDilateIterations = 3.0;
		int cvDilateBordertype = Core.BORDER_CONSTANT;
		Scalar cvDilateBordervalue = new Scalar(-1);
		cvDilate(cvDilateSrc, cvDilateKernel, cvDilateAnchor, cvDilateIterations, cvDilateBordertype, cvDilateBordervalue, cvDilateOutput);

		// Step CV_erode0:
		Mat cvErode0Src = cvDilateOutput;
		Mat cvErode0Kernel = new Mat();
		Point cvErode0Anchor = new Point(-1, -1);
		double cvErode0Iterations = 3.0;
		int cvErode0Bordertype = Core.BORDER_CONSTANT;
		Scalar cvErode0Bordervalue = new Scalar(-1);
		cvErode(cvErode0Src, cvErode0Kernel, cvErode0Anchor, cvErode0Iterations, cvErode0Bordertype, cvErode0Bordervalue, cvErode0Output);

		// Step CV_bitwise_and0:
		Mat cvBitwiseAnd0Src1 = cvDilateOutput;
		Mat cvBitwiseAnd0Src2 = cvErode0Output;
		cvBitwiseAnd(cvBitwiseAnd0Src1, cvBitwiseAnd0Src2, cvBitwiseAnd0Output);

		// Step HSV_Threshold0:
		Mat hsvThresholdInput = cvBitwiseAnd0Output;
		double[] hsvThresholdHue = {26.832093647116203, 90.78461791170082};
		double[] hsvThresholdSaturation = {0.0, 46.97564381011482};
		double[] hsvThresholdValue = {238.16531520546278, 255.0};
		hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);

		// Step HSL_Threshold0:
		Mat hslThresholdInput = cvBitwiseAnd0Output;
		double[] hslThresholdHue = {0.0, 180.0};
		double[] hslThresholdSaturation = {0.0, 255.0};
		double[] hslThresholdLuminance = {247.31638418079098, 255.0};
		hslThreshold(hslThresholdInput, hslThresholdHue, hslThresholdSaturation, hslThresholdLuminance, hslThresholdOutput);

		// Step CV_bitwise_and1:
		Mat cvBitwiseAnd1Src1 = hsvThresholdOutput;
		Mat cvBitwiseAnd1Src2 = hslThresholdOutput;
		cvBitwiseAnd(cvBitwiseAnd1Src1, cvBitwiseAnd1Src2, cvBitwiseAnd1Output);

		// Step CV_erode1:
		Mat cvErode1Src = cvBitwiseAnd1Output;
		Mat cvErode1Kernel = new Mat();
		Point cvErode1Anchor = new Point(-1, -1);
		double cvErode1Iterations = 1.0;
		int cvErode1Bordertype = Core.BORDER_CONSTANT;
		Scalar cvErode1Bordervalue = new Scalar(-1);
		cvErode(cvErode1Src, cvErode1Kernel, cvErode1Anchor, cvErode1Iterations, cvErode1Bordertype, cvErode1Bordervalue, cvErode1Output);

		// Step Blur0:
		Mat blurInput = cvErode1Output;
		BlurType blurType = BlurType.get("Box Blur");
		double blurRadius = 4.681713983424438;
		blur(blurInput, blurType, blurRadius, blurOutput);

		// Step Find_Contours0:
		Mat findContoursInput = blurOutput;
		boolean findContoursExternalOnly = false;
		findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

		// Step Convex_Hulls0:
		ArrayList<MatOfPoint> convexHullsContours = findContoursOutput;
		convexHulls(convexHullsContours, convexHullsOutput);

		// Step Filter_Contours0:
		ArrayList<MatOfPoint> filterContoursContours = convexHullsOutput;
		double filterContoursMinArea = 100.0;
		double filterContoursMinPerimeter = 0.0;
		double filterContoursMinWidth = 10.0;
		double filterContoursMaxWidth = 1000.0;
		double filterContoursMinHeight = 18.0;
		double filterContoursMaxHeight = 1000.0;
		double[] filterContoursSolidity = {0.0, 100};
		double filterContoursMaxVertices = 22.0;
		double filterContoursMinVertices = 2.0;
		double filterContoursMinRatio = 0.3;
		double filterContoursMaxRatio = 0.7;
		filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);

	}

	/**
	 * This method is a generated getter for the output of a Resize_Image.
	 * @return Mat output from Resize_Image.
	 */
	public Mat resizeImageOutput() {
		return resizeImageOutput;
	}

	/**
	 * This method is a generated getter for the output of a CV_dilate.
	 * @return Mat output from CV_dilate.
	 */
	public Mat cvDilateOutput() {
		return cvDilateOutput;
	}

	/**
	 * This method is a generated getter for the output of a CV_erode.
	 * @return Mat output from CV_erode.
	 */
	public Mat cvErode0Output() {
		return cvErode0Output;
	}

	/**
	 * This method is a generated getter for the output of a CV_bitwise_and.
	 * @return Mat output from CV_bitwise_and.
	 */
	public Mat cvBitwiseAnd0Output() {
		return cvBitwiseAnd0Output;
	}

	/**
	 * This method is a generated getter for the output of a HSV_Threshold.
	 * @return Mat output from HSV_Threshold.
	 */
	public Mat hsvThresholdOutput() {
		return hsvThresholdOutput;
	}

	/**
	 * This method is a generated getter for the output of a HSL_Threshold.
	 * @return Mat output from HSL_Threshold.
	 */
	public Mat hslThresholdOutput() {
		return hslThresholdOutput;
	}

	/**
	 * This method is a generated getter for the output of a CV_bitwise_and.
	 * @return Mat output from CV_bitwise_and.
	 */
	public Mat cvBitwiseAnd1Output() {
		return cvBitwiseAnd1Output;
	}

	/**
	 * This method is a generated getter for the output of a CV_erode.
	 * @return Mat output from CV_erode.
	 */
	public Mat cvErode1Output() {
		return cvErode1Output;
	}

	/**
	 * This method is a generated getter for the output of a Blur.
	 * @return Mat output from Blur.
	 */
	public Mat blurOutput() {
		return blurOutput;
	}

	/**
	 * This method is a generated getter for the output of a Find_Contours.
	 * @return ArrayList<MatOfPoint> output from Find_Contours.
	 */
	public ArrayList<MatOfPoint> findContoursOutput() {
		return findContoursOutput;
	}

	/**
	 * This method is a generated getter for the output of a Convex_Hulls.
	 * @return ArrayList<MatOfPoint> output from Convex_Hulls.
	 */
	public ArrayList<MatOfPoint> convexHullsOutput() {
		return convexHullsOutput;
	}

	/**
	 * This method is a generated getter for the output of a Filter_Contours.
	 * @return ArrayList<MatOfPoint> output from Filter_Contours.
	 */
	public ArrayList<MatOfPoint> filterContoursOutput() {
		return filterContoursOutput;
	}


	/**
	 * Scales and image to an exact size.
	 * @param input The image on which to perform the Resize.
	 * @param width The width of the output in pixels.
	 * @param height The height of the output in pixels.
	 * @param interpolation The type of interpolation.
	 * @param output The image in which to store the output.
	 */
	private void resizeImage(Mat input, double width, double height,
		int interpolation, Mat output) {
		Imgproc.resize(input, output, new Size(width, height), 0.0, 0.0, interpolation);
	}

	/**
	 * Expands area of higher value in an image.
	 * @param src the Image to dilate.
	 * @param kernel the kernel for dilation.
	 * @param anchor the center of the kernel.
	 * @param iterations the number of times to perform the dilation.
	 * @param borderType pixel extrapolation method.
	 * @param borderValue value to be used for a constant border.
	 * @param dst Output Image.
	 */
	private void cvDilate(Mat src, Mat kernel, Point anchor, double iterations,
	int borderType, Scalar borderValue, Mat dst) {
		if (kernel == null) {
			kernel = new Mat();
		}
		if (anchor == null) {
			anchor = new Point(-1,-1);
		}
		if (borderValue == null){
			borderValue = new Scalar(-1);
		}
		Imgproc.dilate(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
	}

	/**
	 * Segment an image based on hue, saturation, and value ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param val The min and max value
	 * @param output The image in which to store the output.
	 */
	private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val,
	    Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
			new Scalar(hue[1], sat[1], val[1]), out);
	}

	/**
	 * Segment an image based on hue, saturation, and luminance ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param lum The min and max luminance
	 * @param output The image in which to store the output.
	 */
	private void hslThreshold(Mat input, double[] hue, double[] sat, double[] lum,
		Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HLS);
		Core.inRange(out, new Scalar(hue[0], lum[0], sat[0]),
			new Scalar(hue[1], lum[1], sat[1]), out);
	}

	/**
	 * Computes the per channel and of two images.
	 * @param src1 The first image to use.
	 * @param src2 The second image to use.
	 * @param dst the result image when the and is performed.
	 */
	private void cvBitwiseAnd(Mat src1, Mat src2, Mat dst) {
		Core.bitwise_and(src1, src2, dst);
	}

	/**
	 * Expands area of lower value in an image.
	 * @param src the Image to erode.
	 * @param kernel the kernel for erosion.
	 * @param anchor the center of the kernel.
	 * @param iterations the number of times to perform the erosion.
	 * @param borderType pixel extrapolation method.
	 * @param borderValue value to be used for a constant border.
	 * @param dst Output Image.
	 */
	private void cvErode(Mat src, Mat kernel, Point anchor, double iterations,
		int borderType, Scalar borderValue, Mat dst) {
		if (kernel == null) {
			kernel = new Mat();
		}
		if (anchor == null) {
			anchor = new Point(-1,-1);
		}
		if (borderValue == null) {
			borderValue = new Scalar(-1);
		}
		Imgproc.erode(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
	}

	/**
	 * An indication of which type of filter to use for a blur.
	 * Choices are BOX, GAUSSIAN, MEDIAN, and BILATERAL
	 */
	enum BlurType{
		BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
			BILATERAL("Bilateral Filter");

		private final String label;

		BlurType(String label) {
			this.label = label;
		}

		public static BlurType get(String type) {
			if (BILATERAL.label.equals(type)) {
				return BILATERAL;
			}
			else if (GAUSSIAN.label.equals(type)) {
			return GAUSSIAN;
			}
			else if (MEDIAN.label.equals(type)) {
				return MEDIAN;
			}
			else {
				return BOX;
			}
		}

		@Override
		public String toString() {
			return this.label;
		}
	}

	/**
	 * Softens an image using one of several filters.
	 * @param input The image on which to perform the blur.
	 * @param type The blurType to perform.
	 * @param doubleRadius The radius for the blur.
	 * @param output The image in which to store the output.
	 */
	private void blur(Mat input, BlurType type, double doubleRadius,
		Mat output) {
		int radius = (int)(doubleRadius + 0.5);
		int kernelSize;
		switch(type){
			case BOX:
				kernelSize = 2 * radius + 1;
				Imgproc.blur(input, output, new Size(kernelSize, kernelSize));
				break;
			case GAUSSIAN:
				kernelSize = 6 * radius + 1;
				Imgproc.GaussianBlur(input,output, new Size(kernelSize, kernelSize), radius);
				break;
			case MEDIAN:
				kernelSize = 2 * radius + 1;
				Imgproc.medianBlur(input, output, kernelSize);
				break;
			case BILATERAL:
				Imgproc.bilateralFilter(input, output, -1, radius, radius);
				break;
		}
	}

	/**
	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
	 * @param input The image on which to perform the Distance Transform.
	 * @param type The Transform.
	 * @param maskSize the size of the mask.
	 * @param output The image in which to store the output.
	 */
	private void findContours(Mat input, boolean externalOnly,
		List<MatOfPoint> contours) {
		Mat hierarchy = new Mat();
		contours.clear();
		int mode;
		if (externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, contours, hierarchy, mode, method);
	}

	/**
	 * Compute the convex hulls of contours.
	 * @param inputContours The contours on which to perform the operation.
	 * @param outputContours The contours where the output will be stored.
	 */
	private void convexHulls(List<MatOfPoint> inputContours,
		ArrayList<MatOfPoint> outputContours) {
		final MatOfInt hull = new MatOfInt();
		outputContours.clear();
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final MatOfPoint mopHull = new MatOfPoint();
			Imgproc.convexHull(contour, hull);
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int) hull.get(j, 0)[0];
				double[] point = new double[] {contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			outputContours.add(mopHull);
		}
	}


	/**
	 * Filters out contours that do not meet certain criteria.
	 * @param inputContours is the input list of contours
	 * @param output is the the output list of contours
	 * @param minArea is the minimum area of a contour that will be kept
	 * @param minPerimeter is the minimum perimeter of a contour that will be kept
	 * @param minWidth minimum width of a contour
	 * @param maxWidth maximum width
	 * @param minHeight minimum height
	 * @param maxHeight maximimum height
	 * @param Solidity the minimum and maximum solidity of a contour
	 * @param minVertexCount minimum vertex Count of the contours
	 * @param maxVertexCount maximum vertex Count
	 * @param minRatio minimum ratio of width to height
	 * @param maxRatio maximum ratio of width to height
	 */
	private void filterContours(List<MatOfPoint> inputContours, double minArea,
		double minPerimeter, double minWidth, double maxWidth, double minHeight, double
		maxHeight, double[] solidity, double maxVertexCount, double minVertexCount, double
		minRatio, double maxRatio, List<MatOfPoint> output) {
		final MatOfInt hull = new MatOfInt();
		output.clear();
		//operation
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < minWidth || bb.width > maxWidth) continue;
			if (bb.height < minHeight || bb.height > maxHeight) continue;
			final double area = Imgproc.contourArea(contour);
			if (area < minArea) continue;
			if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter) continue;
			Imgproc.convexHull(contour, hull);
			MatOfPoint mopHull = new MatOfPoint();
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int)hull.get(j, 0)[0];
				double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			final double solid = 100 * area / Imgproc.contourArea(mopHull);
			if (solid < solidity[0] || solid > solidity[1]) continue;
			if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)	continue;
			final double ratio = bb.width / (double)bb.height;
			if (ratio < minRatio || ratio > maxRatio) continue;
			output.add(contour);
		}
	}


	public double getCenterX(){
		for(MatOfPoint m : filterContoursOutput() ){
			Rect w = Imgproc.boundingRect(m);
			System.out.print(w.x + "  ");
		}
		System.out.println();
		if(filterContoursOutput().isEmpty()){
			return -1;
		}else if(filterContoursOutput().size() == 1){
			Rect r= Imgproc.boundingRect(filterContoursOutput().get(0));
			//System.out.println(r.x);
			return r.x + (r.width/2);	
		}else{
			Rect r1= Imgproc.boundingRect(filterContoursOutput().get(0));
			Rect r2= Imgproc.boundingRect(filterContoursOutput().get(1));
			Rect r = averageBoxes(r1,r2);
			//System.out.println(r.x);
			return r.x + (r.width/2);
		}
//		}else if(filterContoursOutput().size() == 2){
////			Rect r= Imgproc.boundingRect(getLargestAreaMat(filterContoursOutput()));
////			Rect r2= Imgproc.boundingRect(getSecondLargestAreaMat(filterContoursOutput()));
//			Rect r1= Imgproc.boundingRect(filterContoursOutput().get(0));
//			Rect r2= Imgproc.boundingRect(filterContoursOutput().get(1));
//			Rect r = averageBoxes(r1,r2);
//			//System.out.println(r.x);
//			return r.x + (r.width/2);
////			return .5 * ((r.x + (r.width / 2)) + (r2.x + (r2.width / 2)));
//		}else{
//			Rect r = getCentralRect(filterContoursOutput());
//			if(r != null){
//				//System.out.println(r.x);
//				return r.x + (r.width);
//			}else{
//				return -1;
//			}

		
	}
	public double getCenterY(){
		if(filterContoursOutput().isEmpty()){
			return -1;
		}else if(filterContoursOutput().size() == 1){
			Rect r= Imgproc.boundingRect(filterContoursOutput().get(0));
			return r.y + (r.height / 2);			
		}else{
			Rect r= Imgproc.boundingRect(getLargestAreaMat(filterContoursOutput()));
			Rect r2= Imgproc.boundingRect(getSecondLargestAreaMat(filterContoursOutput()));
			
			return .5 * ((r.y + (r.height / 2)) + (r2.y + (r2.height / 2)));
		}
	}
	public double getHeight(){
		if(filterContoursOutput().isEmpty()){
			return -1;			
		}else if(filterContoursOutput().size() == 1){
			return Imgproc.boundingRect(filterContoursOutput().get(0)).height;
		}else{
			return Imgproc.boundingRect(getLargestAreaMat(filterContoursOutput())).height;
		}
	}
	public double getWidth(){
		if(filterContoursOutput().isEmpty()){
			return -1;			
		}else if(filterContoursOutput().size() == 1){
				return Imgproc.boundingRect(filterContoursOutput().get(0)).width;
		}else{
				return Imgproc.boundingRect(getLargestAreaMat(filterContoursOutput())).width;
		}
	}
	
	public double getArea(){
		if(filterContoursOutput().isEmpty()){
			return -1;			
		}else if(filterContoursOutput().size() == 1){
				return Imgproc.boundingRect(filterContoursOutput().get(0)).area();
		}else if(filterContoursOutput().size() == 2){
			Rect r1= Imgproc.boundingRect(filterContoursOutput().get(0));
			Rect r2= Imgproc.boundingRect(filterContoursOutput().get(1));
			Rect r = averageBoxes(r1,r2);
			return r.area();
		}else{
			Rect r = getCentralRect(filterContoursOutput());
			if(r != null){
				return r.area();
			}else{
				return -1;
			}
		}
	}
	public MatOfPoint getLargestAreaMat(ArrayList<MatOfPoint> arr) {
		MatOfPoint ret = arr.get(0);
		for(MatOfPoint m : arr){
			if(Imgproc.boundingRect(m).area() > Imgproc.boundingRect(ret).area()){
				ret = m;
			}
		}
		return ret;
	}
	public MatOfPoint getSecondLargestAreaMat(ArrayList<MatOfPoint> arr){
		ArrayList<MatOfPoint> loc = (ArrayList<MatOfPoint>) arr.clone();
		loc.remove(getLargestAreaMat(loc));
		return getLargestAreaMat(loc);
	}
	
	public Rect getCentralRect(ArrayList<MatOfPoint> arr){
		for(MatOfPoint m : arr){
			Rect r = Imgproc.boundingRect(m);
			for(MatOfPoint n: arr){
				Rect r2 = Imgproc.boundingRect(n);
				if(VortxMath.isWithinThreshold(r.y, r2.y, 15)){
				   //VortxMath.isWithinThreshold(r.area(), r2.area(), 100))
					
					return(averageBoxes(r,r2));
				}
			}
		}
		return null;
	}
	
	public Rect averageBoxes(Rect r, Rect r2){
		Rect left;
		Rect right;
		if(r.x < r2.x){
			left = r;
			right = r2;
		}else{
			left = r2;
			right = r;
		}
		
		return new Rect(left.x,
				left.y,
				(right.x + right.width) - left.x,
				(left.height + right.height)/2);
	}




}
