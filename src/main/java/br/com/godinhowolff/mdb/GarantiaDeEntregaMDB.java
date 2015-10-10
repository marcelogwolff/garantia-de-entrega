package br.com.godinhowolff.mdb;
/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "GarantiaDeEntregaMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/teste"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class GarantiaDeEntregaMDB implements MessageListener {
    private final static Logger LOG = Logger.getLogger(GarantiaDeEntregaMDB.class.toString());
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            msg = (TextMessage) rcvMessage;
            System.out.println(msg.getText());
            LOG.info("Mensagem recebida da fila: " + msg.getText());
            
            //Gerando um number exception error
            //Nesse momento uma excessão é lançada e a mensagem 
            //fica na fila para ser processada no tempo configurado
            Integer.valueOf("s");
            
            
        } catch (JMSException e) {
        	e.printStackTrace();
            throw new RuntimeException("Erro ao consumir a mensagem");
        } catch (NumberFormatException n){
            throw new RuntimeException("Erro ao formatar número");
        }
    }
}
