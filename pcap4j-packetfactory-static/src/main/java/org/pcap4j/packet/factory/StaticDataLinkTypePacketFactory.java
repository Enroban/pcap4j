/*_##########################################################################
  _##
  _##  Copyright (C) 2012-2015  Pcap4J.org
  _##
  _##########################################################################
*/

package org.pcap4j.packet.factory;

import org.pcap4j.packet.BsdLoopbackPacket;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.HdlcPppPacket;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.LinuxSllPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.PppSelector;
import org.pcap4j.packet.namednumber.DataLinkType;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.14
 */
public final class StaticDataLinkTypePacketFactory
extends AbstractStaticPacketFactory<DataLinkType> {

  private static final StaticDataLinkTypePacketFactory INSTANCE
    = new StaticDataLinkTypePacketFactory();

  private StaticDataLinkTypePacketFactory() {
    instantiaters.put(
      DataLinkType.EN10MB, new PacketInstantiater() {
        @Override
        public Packet newInstance(
          byte[] rawData, int offset, int length
        ) throws IllegalRawDataException {
          return EthernetPacket.newPacket(rawData, offset, length);
        }
        @Override
        public Class<EthernetPacket> getTargetClass() {
          return EthernetPacket.class;
        }
      }
    );
    instantiaters.put(
      DataLinkType.PPP, new PacketInstantiater() {
        @Override
        public Packet newInstance(
          byte[] rawData, int offset, int length
        ) throws IllegalRawDataException {
          return PppSelector.newPacket(rawData, offset, length);
        }
        @Override
        public Class<PppSelector> getTargetClass() {
          return PppSelector.class;
        }
      }
    );
    instantiaters.put(
      DataLinkType.RAW, new PacketInstantiater() {
        @Override
        public Packet newInstance(
          byte[] rawData, int offset, int length
        ) throws IllegalRawDataException {
          return IpPacket.newPacket(rawData, offset, length);
        }
        @Override
        public Class<IpPacket> getTargetClass() {
          return IpPacket.class;
        }
      }
    );
    instantiaters.put(
      DataLinkType.PPP_SERIAL, new PacketInstantiater() {
        @Override
        public Packet newInstance(
          byte[] rawData, int offset, int length
        ) throws IllegalRawDataException {
          return HdlcPppPacket.newPacket(rawData, offset, length);
        }
        @Override
        public Class<HdlcPppPacket> getTargetClass() {
          return HdlcPppPacket.class;
        }
      }
    );
    instantiaters.put(
      DataLinkType.LINUX_SLL, new PacketInstantiater() {
        @Override
        public Packet newInstance(
          byte[] rawData, int offset, int length
        ) throws IllegalRawDataException {
          return LinuxSllPacket.newPacket(rawData, offset, length);
        }
        @Override
        public Class<LinuxSllPacket> getTargetClass() {
          return LinuxSllPacket.class;
        }
      }
    );
    instantiaters.put(
      DataLinkType.NULL, new PacketInstantiater() {
        @Override
        public Packet newInstance(
          byte[] rawData, int offset, int length
        ) throws IllegalRawDataException {
          return BsdLoopbackPacket.newPacket(rawData, offset, length);
        }
        @Override
        public Class<BsdLoopbackPacket> getTargetClass() {
          return BsdLoopbackPacket.class;
        }
      }
    );
  };

  /**
   *
   * @return the singleton instance of StaticDataLinkTypePacketFactory.
   */
  public static StaticDataLinkTypePacketFactory getInstance() {
    return INSTANCE;
  }

}
