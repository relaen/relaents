import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Tunnel} from './tunnel'

@Entity("t_tunnel_type",'tunnel')
export class TunnelType extends BaseEntity{
	@Id()
	@Column({
		name:'tunnel_type_id',
		type:'int',
		nullable:false
	})
	private tunnelTypeId:number;

	@Column({
		name:'tunnel_type_name',
		type:'string',
		nullable:true
	})
	private tunnelTypeName:string;

	@Column({
		name:'is_twoway',
		type:'int',
		nullable:true
	})
	private isTwoway:number;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	private remarks:string;

	@OneToMany({entity:'Tunnel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'tunnelType',eager:false})
	private tunnels:Array<Tunnel>;

	public getTunnelTypeId():number{
		return this.tunnelTypeId;
	}
	public setTunnelTypeId(value:number){
		this.tunnelTypeId = value;
	}

	public getTunnelTypeName():string{
		return this.tunnelTypeName;
	}
	public setTunnelTypeName(value:string){
		this.tunnelTypeName = value;
	}

	public getIsTwoway():number{
		return this.isTwoway;
	}
	public setIsTwoway(value:number){
		this.isTwoway = value;
	}

	public getRemarks():string{
		return this.remarks;
	}
	public setRemarks(value:string){
		this.remarks = value;
	}

	public getTunnels():Array<Tunnel>{
		return this.tunnels;
	}
	public setTunnels(value:Array<Tunnel>){
		this.tunnels = value;
	}

}