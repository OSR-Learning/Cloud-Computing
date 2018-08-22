package com.osr.cloud.aws.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AmazonWebServicesS3 {

	public static void main(String[] args) throws IOException {
		/*
		 * Create your credentials file at ~/.aws/credentials
		 * (C:\Users\USER_NAME\.aws\credentials for Windows users) and save the
		 * following lines after replacing the underlined values with your own.
		 *
		 * [default] aws_access_key_id = YOUR_ACCESS_KEY_ID aws_secret_access_key =
		 * YOUR_SECRET_ACCESS_KEY
		 */

		AmazonS3 s3 = new AmazonS3Client();
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		s3.setRegion(usWest2);

		String bucketName = "my-first-s3-bucket-" + UUID.randomUUID();
		String key = "MyObjectKey";

		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon S3");
		System.out.println("===========================================\n");
		try {
			/*
			 * Create a new S3 bucket - Amazon S3 bucket names are globally unique, so once
			 * a bucket name has been taken by any user, you can't create another bucket
			 * with that same name.
			 *
			 * You can optionally specify a location for your bucket if you want to keep
			 * your data closer to your applications or users.
			 */
			System.out.println("Creating bucket " + bucketName + "\n");
			s3.createBucket(bucketName);

			/*
			 * List the buckets in your account
			 */
			System.out.println("Listing buckets");
			for (Bucket bucket : s3.listBuckets()) {
				System.out.println(" - " + bucket.getName());
			}
			System.out.println();
			
			
			
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}
}
