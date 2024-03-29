/*
Copyright (c) 2010 tgerm.com
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
   derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, 
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.tgerm.tolerado.axis14.partner;

import javax.xml.rpc.ServiceException;

import com.sforce.soap.partner.DataCategoryGroupSobjectTypePair;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.DescribeDataCategoryGroupResult;
import com.sforce.soap.partner.DescribeDataCategoryGroupStructureResult;
import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeLayoutResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.DescribeSoftphoneLayoutResult;
import com.sforce.soap.partner.DescribeTabSetResult;
import com.sforce.soap.partner.LeadConvert;
import com.sforce.soap.partner.LeadConvertResult;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.SessionHeader;
import com.sforce.soap.partner.SforceServiceLocator;
import com.sforce.soap.partner.SoapBindingStub;
import com.sforce.soap.partner.sobject.SObject;
import com.tgerm.tolerado.axis14.core.Credential;
import com.tgerm.tolerado.axis14.core.ToleradoException;
import com.tgerm.tolerado.axis14.core.ToleradoStub;
import com.tgerm.tolerado.axis14.core.method.WSRecoverableMethod;

/**
 * {@link ToleradoPartnerStub} for partner WSDL
 * 
 * @author abhinav
 * 
 */
public class ToleradoPartnerStub extends ToleradoStub {
	private SoapBindingStub partnerBinding;

	public ToleradoPartnerStub(Credential cred) {
		super(cred);
	}

	/**
	 * @return The {@link SoapBindingStub} for partner wsdl
	 */
	public SoapBindingStub getPartnerBinding() {
		return partnerBinding;
	}

	@Override
	public void prepare() {
		try {
			partnerBinding = (SoapBindingStub) new SforceServiceLocator()
					.getSoap();
		} catch (ServiceException e) {
			throw new ToleradoException("Failed to create SoapBindingStub", e);
		}
		partnerBinding._setProperty(SoapBindingStub.ENDPOINT_ADDRESS_PROPERTY,
				session.getServerUrl());
		SessionHeader sh = new SessionHeader();
		sh.setSessionId(session.getSessionId());
		partnerBinding.setHeader(new SforceServiceLocator().getServiceName()
				.getNamespaceURI(), "SessionHeader", sh);
		partnerBinding.setTimeout(60000);
	}

	/**
	 * Gives the salesforce login result
	 * 
	 */
	public LoginResult getLoginResult() {
		return (LoginResult) session.getLoginResult();
	}

	/**
	 * Queries salesforce via SOQL
	 * 
	 * @param soql
	 * @return
	 */
	public QueryResult query(final String soql) {
		WSRecoverableMethod<QueryResult, ToleradoPartnerStub> wsMethod = new WSRecoverableMethod<QueryResult, ToleradoPartnerStub>(
				"Query") {
			@Override
			protected QueryResult invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				QueryResult query = stub.getPartnerBinding().query(soql);
				return query;
			}
		};
		return wsMethod.invoke(this);
	}

	public QueryResult queryAll(final String soql) {
		return new WSRecoverableMethod<QueryResult, ToleradoPartnerStub>(
				"QueryAll") {
			@Override
			protected QueryResult invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				QueryResult query = stub.getPartnerBinding().queryAll(soql);
				return query;
			}
		}.invoke(this);
	}

	public QueryResult queryMore(final String queryLocator) {
		return new WSRecoverableMethod<QueryResult, ToleradoPartnerStub>(
				"QueryMore") {
			@Override
			protected QueryResult invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				QueryResult query = stub.getPartnerBinding().queryMore(
						queryLocator);
				return query;
			}
		}.invoke(this);
	}

	public LeadConvertResult[] convertLead(final LeadConvert[] leadConverts) {
		return new WSRecoverableMethod<LeadConvertResult[], ToleradoPartnerStub>(
				"convertLead") {
			@Override
			protected LeadConvertResult[] invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				LeadConvertResult[] results = stub.getPartnerBinding()
						.convertLead(leadConverts);
				return results;
			}
		}.invoke(this);
	}

	public DeleteResult[] delete(final String[] ids) {
		return new WSRecoverableMethod<DeleteResult[], ToleradoPartnerStub>(
				"delete") {
			@Override
			protected DeleteResult[] invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				DeleteResult[] results = stub.getPartnerBinding().delete(ids);
				return results;
			}
		}.invoke(this);
	}

	public DescribeDataCategoryGroupResult[] describeDataCategoryGroups(
			final String[] sObjectType) {
		return new WSRecoverableMethod<DescribeDataCategoryGroupResult[], ToleradoPartnerStub>(
				"describeDataCategoryGroups") {
			@Override
			protected DescribeDataCategoryGroupResult[] invokeActual(
					ToleradoPartnerStub stub) throws Exception {
				DescribeDataCategoryGroupResult[] results = stub
						.getPartnerBinding().describeDataCategoryGroups(
								sObjectType);
				return results;
			}
		}.invoke(this);
	}

	public DescribeDataCategoryGroupStructureResult[] describeDataCategoryGroupStructures(
			final DataCategoryGroupSobjectTypePair[] pairs,
			final boolean topCategoriesOnly) {
		return new WSRecoverableMethod<DescribeDataCategoryGroupStructureResult[], ToleradoPartnerStub>(
				"describeDataCategoryGroupStructures") {
			@Override
			protected DescribeDataCategoryGroupStructureResult[] invokeActual(
					ToleradoPartnerStub stub) throws Exception {

				DescribeDataCategoryGroupStructureResult[] results = stub
						.getPartnerBinding()
						.describeDataCategoryGroupStructures(pairs,
								topCategoriesOnly);
				return results;
			}
		}.invoke(this);
	}

	public DescribeGlobalResult describeGlobal() {
		return new WSRecoverableMethod<DescribeGlobalResult, ToleradoPartnerStub>(
				"describeGlobal") {
			@Override
			protected DescribeGlobalResult invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				DescribeGlobalResult results = stub.getPartnerBinding()
						.describeGlobal();
				return results;
			}
		}.invoke(this);
	}

	public DescribeLayoutResult describeLayout(final String sObjectType,
			final String[] recordTypeIds) {
		return new WSRecoverableMethod<DescribeLayoutResult, ToleradoPartnerStub>(
				"describeLayout") {
			@Override
			protected DescribeLayoutResult invokeActual(ToleradoPartnerStub stub)
					throws Exception {

				DescribeLayoutResult results = stub.getPartnerBinding()
						.describeLayout(sObjectType, recordTypeIds);
				return results;
			}
		}.invoke(this);
	}

	public DescribeSObjectResult describeSObject(final String sObjectType) {
		return new WSRecoverableMethod<DescribeSObjectResult, ToleradoPartnerStub>(
				"describeSObject") {
			@Override
			protected DescribeSObjectResult invokeActual(
					ToleradoPartnerStub stub) throws Exception {

				return stub.getPartnerBinding().describeSObject(sObjectType);
			}
		}.invoke(this);
	}

	public DescribeSObjectResult[] describeSObjects(final String[] sObjectType) {
		return new WSRecoverableMethod<DescribeSObjectResult[], ToleradoPartnerStub>(
				"describeSObjects") {
			@Override
			protected DescribeSObjectResult[] invokeActual(
					ToleradoPartnerStub stub) throws Exception {
				return stub.getPartnerBinding().describeSObjects(sObjectType);
			}
		}.invoke(this);
	}

	public DescribeSoftphoneLayoutResult describeSoftphoneLayout() {
		return new WSRecoverableMethod<DescribeSoftphoneLayoutResult, ToleradoPartnerStub>(
				"describeSoftphoneLayout") {
			@Override
			protected DescribeSoftphoneLayoutResult invokeActual(
					ToleradoPartnerStub stub) throws Exception {
				return stub.getPartnerBinding().describeSoftphoneLayout();
			}
		}.invoke(this);
	}

	public DescribeTabSetResult[] describeTabs() {
		return new WSRecoverableMethod<DescribeTabSetResult[], ToleradoPartnerStub>(
				"describeTabs") {
			@Override
			protected DescribeTabSetResult[] invokeActual(
					ToleradoPartnerStub stub) throws Exception {
				return stub.getPartnerBinding().describeTabs();
			}
		}.invoke(this);
	}

	public SaveResult[] create(final SObject[] sObjects) {
		return new WSRecoverableMethod<SaveResult[], ToleradoPartnerStub>(
				"create") {
			@Override
			protected SaveResult[] invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				return stub.getPartnerBinding().create(sObjects);
			}
		}.invoke(this);
	}

	public SaveResult[] update(final SObject[] sObjects) {
		return new WSRecoverableMethod<SaveResult[], ToleradoPartnerStub>(
				"update") {
			@Override
			protected SaveResult[] invokeActual(ToleradoPartnerStub stub)
					throws Exception {
				return stub.getPartnerBinding().update(sObjects);
			}
		}.invoke(this);
	}

}
